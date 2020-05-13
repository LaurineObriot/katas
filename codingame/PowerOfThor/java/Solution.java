import java.util.*;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        /* We could store the coordinates of Thor and its target (4 vars)
           Or like here, store the difference target - Thor (2 vars)
           It is more efficient for comparisons and simpler*/
        int diffX = in.nextInt();
        int diffY = in.nextInt();
        diffX -= in.nextInt();
        diffY -= in.nextInt();

        while (true) {
            if (diffY > 0) {
                System.out.print("S");
                diffY--;
            }
            else if (diffY < 0) {
                System.out.print("N");
                diffY++;
            }

            if (diffX > 0) {
                System.out.print("E");
                diffX--;
            }
            else if (diffX < 0) {
                System.out.print("W");
                diffX++;
            }

            System.out.print("\n");
        }
    }
}
