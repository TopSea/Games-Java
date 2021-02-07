package top.topsea.games.game2048;

public class Array2048 {

    public static int[][] InitializeArray(int width, int length) {
        int[][] array = new int[width][length];
        int firstNum = (int) (Math.random() * 16);
        int x = firstNum / 4;
        int y = firstNum % 4;
        array[x][y] = 2;
        return array;
    }

    public static int[][] RandomNum(int[][] array) {
        int sign = CountZeros(array);
        //没有0游戏结束
        if (sign == -1){
            return null;
        }
        int choose = (int) (Math.random() * sign);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 0){
                    choose--;
                    if (choose == -1){
                        array[i][j] = 2;
                    }
                }
            }
        }
        return array;
    }

    public static int CountZeros(int[][] array) {
//        int temp;
        int countZeros = 0;
//        int horizontal = 0;
//        int vertical = 0;
        for (int i = 0; i < array.length; i++) {
//            temp = array[i][0];
            for (int j = 0; j < array[0].length; j++) {
//                if (temp == 0) {
//                    countZeros++;
//                    continue;
//                }
//                if (temp == array[i][j]) {
//                    horizontal++;
//                }
//                temp = array[i][j];
                if (array[i][j] == 0)
                    countZeros++;
            }
        }
//
//        for (int i = 0; i < array.length; i++) {
//            temp = array[0][i];
//            for (int j = 1; j < array[0].length; j++) {
//                if (temp == array[j][i]) {
//                    vertical++;
//                }
//                temp = array[j][i];
//            }
//        }

        if(countZeros != 0)
            return countZeros;
//        return Math.max(horizontal, vertical);
        return -1;
    }

    public static int getScore(int[][] array){
        int score = -2;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                score += array[i][j];
            }
        }
        return score;
    }

}
