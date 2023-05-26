package com.example.kfdvdflkv;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameActivity extends AppCompatActivity {
    Game field = new Game();
    private float x1, x2;
    private float y1, y2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        field.newField();
        updateGrid();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (deltaX > 0) {
                        field.swipeRight();
                    } else {
                        field.swipeLeft();
                    }
                } else {
                    float deltaY = y2 - y1;
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        if (deltaY > 0) {
                            field.swipeDown();
                        } else {
                            field.swipeUp();
                        }
                    }
                }
                TextView mScore = findViewById(R.id.mScore);
                String score = String.valueOf(field.score);
                String bestScore = "0";
                try (FileInputStream fin = openFileInput("bestScore")) {
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    bestScore = new String(bytes);
                    if (Integer.parseInt(bestScore) < Integer.parseInt(score)) {
                        bestScore = score;
                    }
                } catch (IOException ignored) {

                }
                if(Integer.parseInt(bestScore) <= Integer.parseInt(score)){
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput("bestScore", Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        fos.write(score.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                TextView newBestScore = findViewById(R.id.bestScore);
                mScore.setText(score);
                newBestScore.setText(bestScore);
                updateGrid();
                updateColor();
                game_over();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void updateGrid() {
        GridLayout gl = findViewById(R.id.grid);
        TextView[][] grid = new TextView[4][4];
        for (int i = 0; i < gl.getChildCount(); i++) {
            grid[i / 4][i % 4] = (TextView) gl.getChildAt(i);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                String num = String.valueOf(field.field[i][j]);
                grid[i][j].setText(num);
            }
        }

    }

    public void game_over() {
        TextView game_over = findViewById(R.id.game_over);
        if (!field.gameOver()) {
            game_over.setBackgroundColor(0xffFCFAEF);
            game_over.setTextColor(0xffF0AF7E);
        }
    }

    public void updateColor() {
        GridLayout gl = findViewById(R.id.grid);
        TextView[][] grid = new TextView[4][4];
        for (int i = 0; i < gl.getChildCount(); i++) {
            grid[i / 4][i % 4] = (TextView) gl.getChildAt(i);
            String s = grid[i / 4][i % 4].getText().toString();
            switch (s) {
                case ("0"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffD8D8D6);
                    grid[i / 4][i % 4].setTextColor(0xffD8D8D6);
                    grid[i / 4][i % 4].setTextSize(30);
                    break;
                case ("2"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffE9D7C6);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(30);
                    break;
                case ("4"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffE5C9B1);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(30);
                    break;
                case ("8"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffE4A56E);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(30);
                    break;
                case ("16"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffE4956E);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(28);
                    break;
                case ("32"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFF8364);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(28);
                    break;
                case ("64"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFA6B47);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(28);
                    break;
                case ("128"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffF9E295);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("256"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffF6DB82);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("512"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffF5D363);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("1024"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFCD249);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("2048"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFAD207);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                default:
                    grid[i / 4][i % 4].setBackgroundColor(0xfffff0ff);
            }
        }
    }
}