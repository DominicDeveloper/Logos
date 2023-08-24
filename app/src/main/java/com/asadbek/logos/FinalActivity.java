package com.asadbek.logos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity {
    Button button;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);


        button = findViewById(R.id.btnMe);
        mainLayout = findViewById(R.id.mainLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAsImage();
            }
        });

    }
    private void saveAsImage() {
        mainLayout.setDrawingCacheEnabled(true);
        mainLayout.buildDrawingCache();
        mainLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        Bitmap bitmap = mainLayout.getDrawingCache();
        save(bitmap);
    }

    private void save(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root+"/Download");
        String fieName = "Dominic Azimov.jpg";
        File myFile = new File(file,fieName);
        if (myFile.exists()){
            myFile.delete();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "File save!", Toast.LENGTH_SHORT).show();
            mainLayout.setDrawingCacheEnabled(false);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(FinalActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}