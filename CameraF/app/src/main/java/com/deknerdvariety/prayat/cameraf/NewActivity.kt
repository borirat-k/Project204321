package com.deknerdvariety.prayat.cameraf

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import java.io.File
import android.graphics.Bitmap
import android.graphics.Matrix
import java.nio.file.Files.exists
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.R.attr.bitmap
import android.content.Intent
import android.media.ExifInterface


class NewActivity: AppCompatActivity() {

    private lateinit var preview: ImageView
    private lateinit var sendBtn: ImageButton
    private lateinit var  filename:String
    private var bytedata:ByteArray? = null
    private lateinit var myImage:ImageView
    private lateinit var myBitmap:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_activity)

        preview = findViewById(R.id.imageView)
        val bundle = intent.extras
        if (bundle != null) {
            filename = bundle.getString("data")
        }

        val file_image = File(filename)

        val ei = ExifInterface(filename)
        val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED)




        myImage = findViewById(R.id.imageView) as ImageView
        if (file_image.exists()) {
            myBitmap = BitmapFactory.decodeFile(file_image.getAbsolutePath())


            myBitmap = getResizedBitmap(myBitmap,960,720)

            var rotatedBitmap: Bitmap? = null
            when (orientation) {

                ExifInterface.ORIENTATION_ROTATE_90 -> rotatedBitmap = rotateImage(myBitmap,(90).toFloat())

                ExifInterface.ORIENTATION_ROTATE_180 -> rotatedBitmap = rotateImage(myBitmap, (180).toFloat())

                ExifInterface.ORIENTATION_ROTATE_270 -> rotatedBitmap = rotateImage(myBitmap, (270).toFloat())

                ExifInterface.ORIENTATION_NORMAL -> rotatedBitmap = myBitmap
                else -> rotatedBitmap = myBitmap
            }

            myImage.setImageBitmap(rotatedBitmap)
            //myImage.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, preview.width, preview.height, false))
        }
        //Toast.makeText(this,"$filename",Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
                matrix, true)
    }


}