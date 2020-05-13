import java.util.*;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String enemy1 = in.next();
            int dist1 = in.nextInt();
            String enemy2 = in.next();
            int dist2 = in.nextInt();

            System.out.println(dist1 < dist2 ? enemy1 : enemy2);
        }
    }
}
