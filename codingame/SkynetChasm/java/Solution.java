// This puzzle is a simplified version of the hard puzzle "Skynet : the bridge". The aim is to drive a motorbike to make it jump over a gap at the right speed and time. Validation tests are specific cases, that can be passed by implementing the algorithm given in the problem statement. Accelerate until you have enough speed to cross the gap, keep that speed until its time to jump and brake after the gap.

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
