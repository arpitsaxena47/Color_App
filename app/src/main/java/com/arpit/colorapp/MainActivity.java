package com.arpit.colorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTakePicture;
    private Button btnSaveThePicture;
    private ImageView imgPicture;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private TextView txtRedValue;
    private TextView txtBlueValue;
    private TextView txtGreenValue;
    private  final int CAMERA_IMAGE_REQUEST_CODE = 1000;
    private Bitmap bitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnSaveThePicture = findViewById(R.id.btnsSavePicture);
        imgPicture = findViewById(R.id.imgPicture);
        redSeekBar = findViewById(R.id.seekBarRed);
        greenSeekBar = findViewById(R.id.seekBarGreen);
        blueSeekBar = findViewById(R.id.seekBarBlue);
        txtBlueValue = findViewById(R.id.txtBlueValue);
        txtGreenValue = findViewById(R.id.txtGreenValue);
        txtRedValue = findViewById(R.id.txtRedValue);

        btnTakePicture.setOnClickListener(this);
        btnSaveThePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnTakePicture) {

            int permissionResult = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
            if(permissionResult == PackageManager.PERMISSION_GRANTED)
            {
                PackageManager packageManager = getPackageManager();
                if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_IMAGE_REQUEST_CODE );
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Your device does not have camera",Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.CAMERA},1);
            }
        }
        else
            if (v.getId() == R.id.btnsSavePicture)
        {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_IMAGE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            imgPicture.setImageBitmap(bitmap);
        }
    }
}