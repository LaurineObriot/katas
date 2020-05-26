import java.math.*;
import java.util.*;

class MarsLander {
    private static final int Y_MARGIN = 20;
    private static final int SPEED_MARGIN = 5;
    private static final int MAX_DY = 40;
    private static final int MAX_DX = 20;
    private static final double GRAVITY = 3.711;

    private int x, y, dx, dy, fuel, angle, power;
    private int targetL, targetR, targetY;

    public MarsLander() {}

    public void init (int x, int y, int dx, int dy, int fuel, int angle, int power) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.fuel = fuel;
        this.angle = angle;
        this.power = power;
    }

    public void setTarget(int targetL, int targetR, int targetY) {
        this.targetL = targetL;
        this.targetR = targetR;
        this.targetY = targetY;
    }

    public boolean isOverTarget() {
        return targetL <= x && x <= targetR;
    }

    public boolean isFinishing() {
        return y < targetY + Y_MARGIN;
    }

    public boolean hasSafeSpeed() {
        return Math.abs(dx) <= MAX_DX - SPEED_MARGIN &&
               Math.abs(dy) <= MAX_DY - SPEED_MARGIN;
    }

    public boolean goesInWrongDirection() {
        return (x < targetL && dx < 0) || (targetR < x && dx > 0);
    }

    public boolean goesTooFastHorizontally() {
        return Math.abs(dx) > 4 * MAX_DX;
    }

    public boolean goesTooSlowHorizontally() {
        return Math.abs(dx) < 2 * MAX_DX;
    }

    /**
     * returns the best angle to slow down marse lander
     * (the angle directing thrust in the opposition direction to the mvmt)
     */
    public int angleToSlow() {
        double speed = Math.sqrt(dx * dx + dy * dy);
        return (int) Math.toDegrees(Math.asin((double)dx / speed));
    }

    /**
     * returns the exact angle to compensate gravity while
     * going toward target
     */
    public int angleToAimTarget() {
        int angle = (int) Math.toDegrees(Math.acos(GRAVITY / 4.0));
        if (x < targetL)
            return -angle;
        else if (targetR < x)
            return angle;
        else
            return 0;
    }

    /**
     * returns the thrust power needed to aim a null vertical speed
     */
    public int powerToHover() {
        return (dy >= 0) ? 3 : 4;
    }
}

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        MarsLander ship = new MarsLander();
        int N = in.nextInt();

        // Looking for the landing area
        int landX, landY, prevX, prevY;
        prevX = prevY = -1;
        for (int i = 0; i < N; i++) {
            landX = in.nextInt();
            landY = in.nextInt();
            if (landY == prevY) {
                ship.setTarget(prevX, landX, landY);
            } else {
                prevX = landX;
                prevY = landY;
            }
        }

        for (;;) {

            /* The flight follows 2 steps :
            - first the rover goes over the landing zone by
                -- slowing if it goes faster than 4*MAX_HS, or in the wrong direction
                -- accelerating while hovering until it reaches 2*MAX_HS if it goes in the right direction
                -- waiting hovering if it has a speed between 2*MAX_HS and 4*MAX_HS

            - then it slows down to meet speed specification (going back
            to step 1 if it goes out of the landing zone)
            */

            ship.init(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());

            if (!ship.isOverTarget()) {
                if (ship.goesInWrongDirection() || ship.goesTooFastHorizontally())
                    System.out.println(ship.angleToSlow() + " 4");
                else if (ship.goesTooSlowHorizontally())
                    System.out.println(ship.angleToAimTarget() + " 4");
                else
                    System.out.println("0 " + ship.powerToHover());
            }
            else {
                if (ship.isFinishing())
                    System.out.println("0 3");
                else if (ship.hasSafeSpeed())
                    System.out.println("0 2");
                else
                    System.out.println(ship.angleToSlow() + " 4");
            }
        }
    }
}
