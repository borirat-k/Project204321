package com.deknerdvariety.prayat.sendtoserver;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Upload extends AsyncTask<Void,Void,String> {
    private Bitmap image;
    private String name;

    public Upload(Bitmap image,String name){
        this.image = image;
        this.name = name;
    }

    @Override
    protected String doInBackground(Void... params) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //compress the image to jpg format
        image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        /*
         * encode image to base64 so that it can be picked by saveImage.php file
         * */
        String encodeImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        //generate hashMap to store encodedImage and the name
        HashMap<String,String> detail = new HashMap<>();
        detail.put("name", name);
        detail.put("image", encodeImage);

        try{
            //convert this HashMap to encodedUrl to send to php file
            String dataToSend = hashMapToUrl(detail);
            //make a Http request and send data to saveImage.php file
            String response = Request.post("http://192.168.0.102/upload.php",dataToSend);

            //return the response
            return response;

        }catch (Exception e){
            e.printStackTrace();
            Log.e(MainActivity.class.getSimpleName(),"ERROR  "+e);
            return null;
        }
    }

    private String hashMapToUrl(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }




    @Override
    protected void onPostExecute(String s) {
        //show image uploaded
        //Toast.makeText(,"Image Uploaded",Toast.LENGTH_SHORT).show();
        Log.i("Upload","upload success");
    }
}
