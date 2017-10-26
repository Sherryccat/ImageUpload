package com.sihan.bidr_uploadimage;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int result_uploadImage = 1;

    ImageView itemImage;
    android.widget.Button upLoadImageButton;
    EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        itemImage = (ImageView)findViewById(R.id.itemImage);
        upLoadImageButton = (Button)findViewById(R.id.uploadImage);
        itemName = (EditText)findViewById(R.id.itemName);

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
}
