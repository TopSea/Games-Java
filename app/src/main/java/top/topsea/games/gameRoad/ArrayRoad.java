package top.topsea.games.gameRoad;

import java.util.Arrays;

public class ArrayRoad {
    public static int[][] InitializeArray() {
        int[][] array = new int[4][4];
        int[] list = ListArray();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = list[index];
                index++;
            }
        }
        ActionRoad.RandomPosition(array);
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


    private static int[] ListArray() {
        int[] array = new int[16];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }
}
