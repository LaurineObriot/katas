import java.util.*;

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int before = in.nextInt();
        int gap = in.nextInt();
        int after = in.nextInt();

        while (true) {
            // x = motorbike abscissa ; dx = motorbike speed
            int dx = in.nextInt();
            int x = in.nextInt();

            // if the bike isn't going fast enough to jump over the gap
            if ((x >= before + gap) || (dx > gap + 1))
                System.out.println("SLOW");
            // if the bike is going slower than needed : speed
            else if (dx <= gap)
                System.out.println("SPEED");
            // if the bike can land after the gap : jump
            else if (x + dx >= before + gap)
                System.out.println("JUMP");
            else
                System.out.println("WAIT");
        }
    }
}
