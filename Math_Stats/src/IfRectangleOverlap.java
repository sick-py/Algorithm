public class IfRectangleOverlap {
    /**
     * Given the bottom-left and top-right coordinates of two axis-aligned rectangles, check if the two rectangles overlap. Return true if they overlap, otherwise, return false.
     * In this solution, we’ll use the following intuition:
     *
     * For overlapping rectangles, the bottom left (x1, y1) coordinates of the Rectangle1 are always less than the top-right coordinates of the overlapping Rectangle2.
     * Similarly, the bottom left (x1, y1) coordinates of the Rectangle2 are always less than the top-right coordinates of the Rectangle1.
     * We return true if all of the above conditions are met. Otherwise, we return false.
     * */
    boolean isOverlap(int[] rec1, int[] rec2) {
        // Comparing bottom left coordinates of rec1 with the
        // top-right coordinates of rec2
        Boolean xRec1 = rec1[0] < rec2[2];
        Boolean yRec1 = rec1[1] < rec2[3];

        // Comparing bottom left coordinates of rec2 with the
        // top-right coordinates of rec1
        Boolean xRec2 = rec2[0] < rec1[2];
        Boolean yRec2 = rec2[1] < rec1[3];

        // Return true if all of these conditions meet, otherwise return false.
        return (xRec1 && yRec1 && xRec2 && yRec2);
    }
}
