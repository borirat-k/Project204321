package com.deknerdvariety.prayat.opencv

import android.content.Intent
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.media.MediaActionSound
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.TextureView
import android.widget.Button
import android.widget.ToggleButton
import com.deknerdvariety.prayat.opencv.CameraUtil
import com.deknerdvariety.prayat.opencv.CameraUtil.createImageFile

import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

import java.io.IOException

class MainActivity : AppCompatActivity(), TextureView.SurfaceTextureListener{

    private val TAG = MainActivity::class.java!!.getSimpleName()

    private val cameraId = Camera.CameraInfo.CAMERA_FACING_BACK

    private var buttonCapture: Button? = null
    private var buttonFocus: Button? = null
    private var toggleButtonFlash: ToggleButton? = null
    private var textureViewCamera: TextureView? = null

    private var camera: Camera? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)

        buttonCapture = findViewById<Button>(R.id.btnCamera)
        buttonFocus = findViewById(R.id.buttonFocus)
        toggleButtonFlash = findViewById(R.id.toggleButtonFlash)
        textureViewCamera = findViewById(R.id.textureViewCamera)
//
        buttonCapture!!.setOnClickListener { view -> takePicture() }
        buttonFocus!!.setOnClickListener { view -> refocus() }
//        toggleButtonFlash.setOnCheckedChangeListener { buttonView, isChecked -> toggleNegativeColor(isChecked) }
        textureViewCamera!!.setSurfaceTextureListener(this)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
    }

    override fun onStart() {
        super.onStart()
        if (textureViewCamera!!.isAvailable()) {
            setupCamera(textureViewCamera!!.getWidth(), textureViewCamera!!.getHeight())
            startCameraPreview(textureViewCamera!!.getSurfaceTexture())
        }
    }

    override fun onStop() {
        super.onStop()
        stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        textureViewCamera!!.setSurfaceTextureListener(null)
    }


    private fun setupCamera(width: Int, height: Int) {
        camera = CameraUtil.openCamera(cameraId)
        val parameters = camera!!.getParameters()
        val bestPreviewSize = CameraUtil.getBestPreviewSize(parameters.supportedPreviewSizes, width, height)
        parameters.setPreviewSize(bestPreviewSize!!.width, bestPreviewSize.height)
        val bestPictureSize = CameraUtil.getBestPictureSize(parameters.supportedPictureSizes)
        parameters.setPictureSize(bestPictureSize!!.width, bestPictureSize.height)
        if (CameraUtil.isContinuousFocusModeSupported(parameters.supportedFocusModes)) {
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
        }
        camera!!.setParameters(parameters)
        camera!!.setDisplayOrientation(CameraUtil.getCameraDisplayOrientation(this, cameraId))
        textureViewCamera!!.setTransform(CameraUtil.getCropCenterScaleMatrix(width.toFloat(), height.toFloat(), bestPreviewSize.width.toFloat(), bestPreviewSize.height.toFloat()))
    }


    private fun startCameraPreview(surfaceTexture: SurfaceTexture?) {
        try {
            camera!!.setPreviewTexture(surfaceTexture)
            camera!!.startPreview()
        } catch (e: IOException) {
            Log.e(TAG, "Error start camera preview: " + e.message)
        }

    }

    private fun stopCamera() {
        try {
            camera!!.stopPreview()
            camera!!.release()
        } catch (e: Exception) {
            Log.e(TAG, "Error stop camera preview: " + e.message)
        }

    }

    private fun takePicture() {
        val imageFile = createImageFile()
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val authorities = packageName + ".fileprovider"
        val imageUri = FileProvider.getUriForFile(this@MainActivity, authorities, imageFile)
        callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
//        var data:Byte;
//        camera!!.takePicture(this::playShutterSound,null,null,(data,camera))
//
//        camera.takePicture(Camera.ShutterCallback { this.playShutterSound() },
//                null, null
//        ) { data, camera ->
//            val file = CameraUtil.savePicture(data)
//            val orientation = CameraUtil.getCameraDisplayOrientation(this, cameraId)
//            CameraUtil.setImageOrientation(file, orientation)
//            CameraUtil.updateMediaScanner(this, file)
//        }

    }

    private fun refocus() {
        camera!!.autoFocus { success, camera -> playFocusSound() }
    }

    private fun playShutterSound() {
        val sound = MediaActionSound()
        sound.play(MediaActionSound.SHUTTER_CLICK)
    }

    private fun playFocusSound() {
        val sound = MediaActionSound()
        sound.play(MediaActionSound.FOCUS_COMPLETE)
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        stopCamera()
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        setupCamera(width, height)
        startCameraPreview(surface)
    }



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
