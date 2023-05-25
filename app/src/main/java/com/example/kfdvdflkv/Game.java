package com.example.kfdvdflkv;

import java.util.Random;

public class Game {
    int[][] field = new int[4][4];
    int size = field.length;
    Random random = new Random();
    int score = 0;
    boolean fieldHasChanged = true;

    public void spawnDigit() {
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        int randomNumber = (random.nextInt(5) / 4) * 2 + 2;
        while (field[x][y] != 0) {
            x = random.nextInt(size);
            y = random.nextInt(size);
        }
        field[x][y] = randomNumber;
    }

    public void newField() {
        if (fieldHasChanged) spawnDigit();
    }

    public void swipeLeft() {
        fieldHasChanged = false;
        for (int i = 0; i < size; i++) {
            boolean[] summed = {false, false, false, false};
            for (int j = 1; j < size; j++) {
                if (field[i][j] != 0) {
                    int k = j;
                    while (k > 0 && field[i][k - 1] == 0) {
                        field[i][k - 1] = field[i][k];
                        field[i][k] = 0;
                        k--;
                        fieldHasChanged = true;
                    }
                    if (k > 0 && field[i][k - 1] == field[i][k] && !summed[k - 1]) {
                        field[i][k - 1] *= 2;
                        score += field[i][k - 1];
                        field[i][k] = 0;
                        summed[k - 1] = true;
                        fieldHasChanged = true;
                    }
                }
            }
        }
        newField();
    }

    public void swipeRight() {
        fieldHasChanged = false;
        for (int i = 0; i < size; i++) {
            boolean[] summed = {false, false, false, false};
            for (int j = size - 2; j >= 0; j--) {
                if (field[i][j] != 0) {
                    int k = j;
                    while (k < size - 1 && field[i][k + 1] == 0) {
                        field[i][k + 1] = field[i][k];
                        field[i][k] = 0;
                        k++;
                        fieldHasChanged = true;
                    }
                    if (k < size - 1 && field[i][k + 1] == field[i][k] && !summed[k + 1]) {
                        field[i][k + 1] *= 2;
                        score += field[i][k + 1];
                        field[i][k] = 0;
                        summed[k + 1] = true;
                        fieldHasChanged = true;
                    }
                }
            }
        }
        newField();
    }

    public void swipeUp() {
        fieldHasChanged = false;
        for (int j = 0; j < size; j++) {
            boolean[] summed = {false, false, false, false};
            for (int i = 1; i < size; i++) {
                if (field[i][j] != 0) {
                    int k = i;
                    while (k > 0 && field[k - 1][j] == 0) {
                        field[k - 1][j] = field[k][j];
                        field[k][j] = 0;
                        k--;
                        fieldHasChanged = true;
                    }
                    if (k > 0 && field[k - 1][j] == field[k][j] && !summed[k - 1]) {
                        field[k - 1][j] *= 2;
                        score += field[k - 1][j];
                        field[k][j] = 0;
                        summed[k - 1] = true;
                        fieldHasChanged = true;
                    }
                }
            }
        }
        newField();
    }

    public void swipeDown() {
        fieldHasChanged = false;
        for (int i = size - 2; i >= 0; i--) {
            boolean[] summed = {false, false, false, false};
            for (int j = 0; j < size; j++) {
                if (field[i][j] != 0) {
                    int k = i;
                    while (k < size - 1 && field[k + 1][j] == 0) {
                        field[k + 1][j] = field[k][j];
                        field[k][j] = 0;
                        k++;
                        fieldHasChanged = true;
                    }
                    if (k < size - 1 && field[k + 1][j] == field[k][j] && !summed[k + 1]) {
                        field[k + 1][j] *= 2;
                        score += field[k + 1][j];
                        field[k][j] = 0;
                        summed[k + 1] = true;
                        fieldHasChanged = true;
                    }
                }
            }
        }
        newField();
    }

    public void setValue(int i, int j, int value) {
        field[i][j] = value;
    }
    public int getValue(int i, int j) {
        return field[i][j];
    }

    public boolean gameOver() {
        boolean isThereAMove = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0) {
                    isThereAMove = true;
                    break;
                }
                if (i < 3 && field[i + 1][j] == field[i][j]) isThereAMove = true;
                if (j < 3 && field[i][j + 1] == field[i][j]) isThereAMove = true;
            }
        }
        return isThereAMove;
    }
}