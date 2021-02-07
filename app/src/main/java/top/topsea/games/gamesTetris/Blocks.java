package top.topsea.games.gamesTetris;

public enum Blocks {
    Square(0, 7, 0, 8, 1, 7, 1, 8, 1),
    T(0, 6, 0, 7, 0, 8, 1, 7, 2),
    I(0, 5, 0, 6, 0, 7, 0, 8, 3),
    S(0, 7, 0, 8, 1, 6, 1, 7, 4),
    J(0, 7, 1, 7, 2, 6, 2, 7, 5),
    Z(0, 6, 0, 7, 1, 7, 1, 8, 6),
    L(0, 7, 1, 7, 2, 7, 2, 8, 7);

    public int x1, y1;
    public int x2, y2;
    public int x3, y3;
    public int x4, y4;
    public int color;

    Blocks(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int color) {
        this.x1= x1; this.x2=x2;
        this.x3= x3; this.x4=x4;
        this.y1= y1; this.y2=y2;
        this.y3= y3; this.y4=y4;
        this.color = color;
    }

    public static Blocks getBlocks(int b){
        switch (b){
            case 1:
                return Square;
            case 2:
                return T;
            case 3:
                return I;
            case 4:
                return S;
            case 5:
                return J;
            case 6:
                return L;
            case 7:
                return Z;
            default:
                return Square;
        }
    }

    public void move(int x, int y) {
        x1 = x1 + x;
        y1 = y1 + y;
        x2 = x2 + x;
        y2 = y2 + y;
        x3 = x3 + x;
        y3 = y3 + y;
        x4 = x4 + x;
        y4 = y4 + y;
    }

    public void turnBlock() {
        int tmp_x1, tmp_y1;
        int tmp_x2, tmp_y2;
        int tmp_x3, tmp_y3;

        tmp_x1 = turnAroundX1(y2);
        tmp_y1 = turnAroundY1(x2);
        x2 = tmp_x1;
        y2 = tmp_y1;

        tmp_x2 = turnAroundX1(y3);
        tmp_y2 = turnAroundY1(x3);
        x3 = tmp_x2;
        y3 = tmp_y2;

        tmp_x3 = turnAroundX1(y4);
        tmp_y3 = turnAroundY1(x4);
        x4 = tmp_x3;
        y4 = tmp_y3;
    }

    public int turnAroundX1(int y) {
        return x1 + y - y1;
    }

    public int turnAroundY1(int x) {
        return y1 - x + x1;
    }

    public int getMinXCoordinate(int x1, int x2, int x3, int x4) {
        return Math.min(Math.min(x1,x2),Math.min(x3,x4));
    }

}