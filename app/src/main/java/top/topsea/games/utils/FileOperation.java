package top.topsea.games.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import top.topsea.games.game2048.Array2048;

public class FileOperation {

    private static final String bestScores = "best.array";
    private static final int scoreLength = 3;

    public static void saveArray(Context context, int[][] array, String fileName) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(ArrayToString(array).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readArray(Context context, String fileName) throws IOException {
        FileInputStream fis = context.openFileInput(fileName);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }

        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
            e.printStackTrace();
        } finally {
            inputStreamReader.close();
        }
        return stringBuilder.toString();
    }

    public static String ArrayToString(int[][] array){
        StringBuilder result = new StringBuilder();
        result.append(array.length).append(" ");
        result.append(array[0].length).append(" ");
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                result.append(ints[j]).append(" ");
            }
        }
        return result.toString();
    }

    public static int[][] StringToArray(String str){
        String[] strArray = str.split(" ");
        int[][] result = new int[Integer.parseInt(strArray[0])][Integer.parseInt(strArray[1])];
        int sign = 2;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = Integer.parseInt(strArray[sign]);
                sign++;
            }
        }
        return result;
    }

    public static void SaveBest(Context context, String score, int index) {
        String[] array = new String[scoreLength];
        array[index - 1] = score;
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < array.length; j++) {
            result.append(array[j]).append(" ");
        }
        try (FileOutputStream fos = context.openFileOutput(bestScores, Context.MODE_PRIVATE)) {
            fos.write(result.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int GetBest(Context context, int index) throws IOException {
        String str = readArray(context, bestScores);
        String[] arrayStr = str.split(" ");
        arrayStr[arrayStr.length - 1] = "0";
        int[] array = new int[arrayStr.length];

        for (int i = 0; i < array.length; i++) {
            Log.d("------------------", arrayStr[i]);
            array[i] = Integer.parseInt(arrayStr[i]);
        }
        return array[index - 1];
    }
}
