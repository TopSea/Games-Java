package top.topsea.games.gameRoad;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class ArrayRoad {
    public static int[][] InitializeArray() {
        int[][] array = new int[4][4];
        int[] list = RandomArray();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = list[index];
                index++;
            }
        }
        return array;
    }

    public static boolean isDone(int[][] array) {
        int[][] done = new int[4][4];
        int index = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                done[i][j] = index;
                index++;
            }
        }
        done[3][3] = 0;
        return Arrays.deepEquals(done, array);
    }

    public static int getScore(int[][] array) {
        int score = -2;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                score += array[i][j];
            }
        }
        return score;
    }

    private static int[] RandomArray() {
        int[] array = new int[16];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int temp;
        int rand = (int) (Math.random() * 15);
        rand = Math.max(rand, 1);
        Log.d("rand", " " + rand);
        for (int i = 0; i < array.length - 2; i+=rand) {
            temp = array[i];
            array[i] = array[i + 2];
            array[i + 2] = temp;
        }

        return array;
    }
}
