package com.example.kfdvdflkv;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
                mScore.setText(score);
                updateGrid();
                updateColor();
                gameOver();
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
    public void gameOver(){
        boolean isThereAMove = false;
        TextView game_over = findViewById(R.id.game_over);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field.field[i][j] == 0) {
                    isThereAMove = true;
                    break;
                }

                if (i < 3 && field.field[i + 1][j] == field.field[i][j]) isThereAMove = true;
                if (j < 3 && field.field[i][j + 1] == field.field[i][j]) isThereAMove = true;
            }
        }
        if(!isThereAMove){
            game_over.setBackgroundColor(0xff1F1D15);
            game_over.setTextColor(0xffF0EEE7);
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
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("32"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFF8364);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
                    break;
                case ("64"):
                    grid[i / 4][i % 4].setBackgroundColor(0xffFA6B47);
                    grid[i / 4][i % 4].setTextColor(0xff575654);
                    grid[i / 4][i % 4].setTextSize(25);
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
                    grid[i / 4][i % 4].setTextSize(20);
                    break;
                default:
                    grid[i / 4][i % 4].setBackgroundColor(0xfffff0ff);
            }
        }
    }
}