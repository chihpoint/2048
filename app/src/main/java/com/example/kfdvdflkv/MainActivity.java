package com.example.kfdvdflkv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Button play = findViewById(R.id.play);
        play.setOnClickListener(this);

        File file = new File(getApplicationContext().getFilesDir(), "bestScore.txt");
        try {
            boolean fileHasBeenCreated = file.createNewFile();
            if(fileHasBeenCreated){
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("bestScore.txt", Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    fos.write("0".getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextView menuScore = findViewById(R.id.bestScore);
        try (FileInputStream fin = openFileInput("bestScore")) {
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String bestScore = new String(bytes);
            menuScore.setText(bestScore);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.play) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }
}