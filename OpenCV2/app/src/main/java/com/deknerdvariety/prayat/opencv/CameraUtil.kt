package com.deknerdvariety.prayat.opencv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.hardware.Camera
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.Surface
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object CameraUtil {
    private val TAG = MainActivity::class.java!!.getSimpleName()
    lateinit var imageFilePath: String

    val numberOfCamera: Int
        get() = Camera.getNumberOfCameras()

    private val currentDate: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
            val date = Date()
            return simpleDateFormat.format(date)
        }

    fun isCameraSupport(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }

    fun openDefaultCamera(): Camera? {
        var camera: Camera? = null
        try {
            camera = Camera.open()
        } catch (e: RuntimeException) {
            Log.e(TAG, "Error open camera: " + e.message)
        }

        return camera
    }

    fun openCamera(cameraId: Int): Camera? {
        var camera: Camera? = null
        try {
            camera = Camera.open(cameraId)
        } catch (e: RuntimeException) {
            Log.e(TAG, "Error open camera: " + e.message)
        }

        return camera
    }

    fun getBestPictureSize(pictureSizeList: List<Camera.Size>): Camera.Size? {
        var bestPictureSize: Camera.Size? = null
        for (pictureSize in pictureSizeList) {
            if (bestPictureSize == null || pictureSize.height >= bestPictureSize.height && pictureSize.width >= bestPictureSize.width) {
                bestPictureSize = pictureSize
            }
        }
        return bestPictureSize
    }

    fun getBestPreviewSize(previewSizeList: List<Camera.Size>, previewWidth: Int, previewHeight: Int): Camera.Size? {
        var bestPreviewSize: Camera.Size? = null
        for (previewSize in previewSizeList) {
            if (bestPreviewSize != null) {
                val diffBestPreviewWidth = Math.abs(bestPreviewSize.width - previewWidth)
                val diffPreviewWidth = Math.abs(previewSize.width - previewWidth)
                val diffBestPreviewHeight = Math.abs(bestPreviewSize.height - previewHeight)
                val diffPreviewHeight = Math.abs(previewSize.height - previewHeight)
                if (diffPreviewWidth + diffPreviewHeight < diffBestPreviewWidth + diffBestPreviewHeight) {
                    bestPreviewSize = previewSize
                }
            } else {
                bestPreviewSize = previewSize
            }
        }
        return bestPreviewSize
    }

    fun isContinuousFocusModeSupported(supportedFocusModes: List<String>?): Boolean {
        if (supportedFocusModes != null && !supportedFocusModes.isEmpty()) {
            for (focusMode in supportedFocusModes) {
                if (focusMode != null && focusMode.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO, ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    fun getCameraDisplayOrientation(activity: Activity, cameraId: Int): Int {
        val cameraInfo = Camera.CameraInfo()
        Camera.getCameraInfo(cameraId, cameraInfo)
        val rotation = activity.windowManager.defaultDisplay.rotation
        var degree = 0
        when (rotation) {
            Surface.ROTATION_0 -> degree = 0
            Surface.ROTATION_90 -> degree = 90
            Surface.ROTATION_180 -> degree = 180
            Surface.ROTATION_270 -> degree = 270
        }
        var orientation = 0
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            orientation = (cameraInfo.orientation + degree) % 360
            orientation = (360 - orientation) % 360
        } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            orientation = (cameraInfo.orientation - degree + 360) % 360
        }
        return orientation
    }

    fun getCropCenterScaleMatrix(viewWidth: Float, viewHeight: Float, previewWidth: Float, previewHeight: Float): Matrix {
        var scaleX = 1.0f
        var scaleY = 1.0f
        if (previewWidth > viewWidth && previewHeight > viewHeight) {
            scaleX = previewWidth / viewWidth
            scaleY = previewHeight / viewHeight
        } else if (previewWidth < viewWidth && previewHeight < viewHeight) {
            scaleY = viewWidth / previewWidth
            scaleX = viewHeight / previewHeight
        } else if (viewWidth > previewWidth) {
            scaleY = viewWidth / previewWidth / (viewHeight / previewHeight)
        } else if (viewHeight > previewHeight) {
            scaleX = viewHeight / previewHeight / (viewWidth / previewWidth)
        }
        return createScaleMatrix(scaleX, scaleY, viewWidth, viewHeight)
    }

    private fun getDiffX(viewWidth: Float, videoWidth: Float): Float {
        return if (viewWidth > videoWidth) viewWidth / videoWidth else videoWidth / viewWidth
    }

    private fun getDiffY(viewHeight: Float, videoHeight: Float): Float {
        return if (viewHeight > videoHeight) viewHeight / videoHeight else videoHeight / viewHeight
    }

    private fun getAspectRatio(width: Float, height: Float): Float {
        return width / height
    }

    private fun getCropCenterX(viewWidth: Float, viewHeight: Float, videoWidth: Float, videoHeight: Float, diffX: Float, diffY: Float, videoAspectRatio: Float): Float {
        val scaleX: Float
        if (viewWidth < videoWidth) {
            if (viewHeight < videoHeight) {
                if (diffX > diffY) {
                    scaleX = viewHeight * videoAspectRatio / viewWidth
                } else {
                    scaleX = 1f
                }
            } else {
                scaleX = viewHeight * videoAspectRatio / viewWidth
            }
        } else {
            if (viewHeight < videoHeight) {
                scaleX = 1f
            } else {
                if (diffX >= diffY) {
                    scaleX = 1f
                } else {
                    scaleX = viewHeight * videoAspectRatio / viewWidth
                }
            }
        }
        return scaleX
    }

    private fun getCropCenterY(viewWidth: Float, viewHeight: Float, videoWidth: Float, videoHeight: Float, diffX: Float, diffY: Float, videoAspectRatio: Float): Float {
        val scaleY: Float
        if (viewHeight < videoHeight) {
            if (viewWidth < videoWidth) {
                if (diffY > diffX) {
                    scaleY = viewWidth / videoAspectRatio / viewHeight
                } else {
                    scaleY = 1f
                }
            } else {
                scaleY = viewWidth / videoAspectRatio / viewHeight
            }
        } else {
            if (viewWidth < videoWidth) {
                scaleY = 1f
            } else {
                if (diffY > diffX) {
                    scaleY = 1f
                } else {
                    scaleY = viewWidth / videoAspectRatio / viewHeight
                }
            }
        }
        return scaleY
    }

    private fun createScaleMatrix(scaleX: Float, scaleY: Float, width: Float, height: Float): Matrix {
        val matrix = Matrix()
        matrix.setScale(scaleX, scaleY, width / 2, height / 2)
        return matrix
    }

    fun setImageOrientation(file: File?, orientation: Int) {
        if (file != null) {
            try {
                val exifInterface = ExifInterface(file.path)
                val orientationValue = getOrientationExifValue(orientation).toString()
                exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, orientationValue)
                exifInterface.saveAttributes()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun getOrientationExifValue(orientation: Int): Int {
        when (orientation) {
            90 -> return ExifInterface.ORIENTATION_ROTATE_90
            180 -> return ExifInterface.ORIENTATION_ROTATE_180
            270 -> return ExifInterface.ORIENTATION_ROTATE_270
            else -> return ExifInterface.ORIENTATION_NORMAL
        }
    }

    fun savePicture(data: ByteArray): File? {
        val fileName = "$currentDate.jpg"
        val filePath = Environment.getExternalStorageDirectory().absolutePath
        val file = File("$filePath/$fileName")
        try {
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()
            val fos = FileOutputStream(file)
            fos.write(data)
            fos.flush()
            fos.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    fun updateMediaScanner(context: Context, file: File) {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(file)
        context.sendBroadcast(intent)
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File = Environment.getExternalStorageDirectory() //getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        if(!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return imageFile
    }
}