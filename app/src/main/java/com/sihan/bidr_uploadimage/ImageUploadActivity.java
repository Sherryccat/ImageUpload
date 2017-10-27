package com.sihan.bidr_uploadimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int result_uploadImage = 1;
    private final String SERVER_ITEM_ADDRESS = "http://52.237.16.136/item_api.php";
    ImageView itemImage;
    android.widget.Button upLoadImageButton;
    EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        itemImage = findViewById(R.id.itemImage);
        upLoadImageButton = findViewById(R.id.uploadImage);
        itemName = findViewById(R.id.itemName);

        itemImage.setOnClickListener(this);
        upLoadImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.itemImage:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, result_uploadImage);
                break;

            case R.id.uploadImage:
                Bitmap image = ((BitmapDrawable)itemImage.getDrawable()).getBitmap();
                new postItem(image, itemName.getText().toString()).execute();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == result_uploadImage && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            itemImage.setImageURI(selectedImage);
        }
    }

    private class postItem extends AsyncTask<Void, Void, Void>{


        Bitmap image;
        String name;

        postItem(Bitmap image, String name ){
            this.image = image;
            this.name = name;
        }



        @Override
        protected Void doInBackground(Void... voids) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

            ArrayMap<String,String> dataToSend = new ArrayMap<>();
            dataToSend.put("image",encodedImage);
            dataToSend.put("name",name);

            //TODO create a variable to hold timeOutParameter


            //TODO add url

            try {
                HttpURLConnection httpPost = new HttpURLConnection(new URL(SERVER_ITEM_ADDRESS)) {
                    @Override
                    public void disconnect() {
 
                    }

                    @Override
                    public boolean usingProxy() {
                        return false;
                    }

                    @Override
                    public void connect() throws IOException {

                    }
                };
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /* TODO create httpParam to get timeout info
    private HttpURLConnection instead
    */

    private class
}
