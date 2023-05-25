package com.example.kfdvdflkv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button play;
    File file = new File("bestScore.txt");
    int bestScore;

    Game game = new Game();

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Button play = findViewById(R.id.play);
        play.setOnClickListener(this);
        System.out.println(28744);
        File file = new File(getApplicationContext().getFilesDir(), "bestScore.txt");
        try {
            boolean ok = file.createNewFile();
            if(ok){
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