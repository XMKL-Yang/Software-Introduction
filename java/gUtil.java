package assignment4;
/**
* Some helper methods to translate simulation coordinates to screen * coordinates
* @author ferrie
*
*/

public class gUtil {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final int OFFSET = 200;
    private static final double SCALE = HEIGHT/100;
/**
* X coordinate to screen x
* @param X
* @return x screen coordinate - integer */
    static int XtoScreen(double X) {
        return (int) (X * SCALE);
}
/**
* Y coordinate to screen y
* @param Y
* @return y screen coordinate - integer */
    static int YtoScreen(double Y) {
        return (int) (HEIGHT - Y * SCALE);
}
/**
* Length to screen length * @param length - double * @return sLen - integer */
// n.b. screen coordinates
// Pixels/meter
static int LtoScreen(double length) {
        return (int) (length * SCALE);
} }