package com.rover;

import java.util.Objects;

public class Rover {

    static int SPEED = 2000;

    private final Console console;
    private Position position;

    public Rover(Position position, Console console) {
        this.position = position;
        this.console = console;
    }

    public String display() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   0 1 2 3 4 5 6 7 8 9\n");
        stringBuilder.append(" +---------------------+\n");

        for (int y = 0; y < 10; y++) {
            stringBuilder.append(y + "| ");

            for (int x = 0; x < 10; x++) {

                if (this.position.x == x && this.position.y == y) {
                    if (this.position.direction == 'N') {
                        stringBuilder.append("^ ");
                    } else if (this.position.direction == 'E') {
                        stringBuilder.append("> ");
                    } else if (this.position.direction == 'W') {
                        stringBuilder.append("< ");
                    } else if (this.position.direction == 'S') {
                        stringBuilder.append("v ");
                    }
                } else {
                    stringBuilder.append("  ");
                }

            }

            stringBuilder.append("|\n");
        }

        stringBuilder.append(" +---------------------+\n");
        return stringBuilder.toString();
    }

    public void run(String[] commands) {

        this.console.print("Initial Position");
        this.console.print(this.display());

        for (String command : commands) {

            this.console.print("Move: " + command);
            if (Objects.equals(command, "f")) {
                if (this.position.direction == 'N') {

                    this.position.y -= 1;
                    if (this.position.y < 0) {
                        this.position.y += 10;
                    }

                } else if (this.position.direction == 'W') {
                    this.position.x -= 1;
                    if (this.position.x < 0) {
                        this.position.x += 10;
                    }
                } else if (this.position.direction == 'E') {
                    this.position.x += 1;
                    if (this.position.x >= 10) {
                        this.position.x -= 10;
                    }
                } else if (this.position.direction == 'S') {
                    this.position.y += 1;
                    if (this.position.y >= 10) {
                        this.position.y -= 10;
                    }
                }
            } else if (Objects.equals(command, "b")) {

                if (this.position.direction == 'N') {
                    this.position.y += 1;
                    if (this.position.y >= 10) {
                        this.position.y -= 10;
                    }
                } else if (this.position.direction == 'W') {

                    this.position.x += 1;
                    if (this.position.x >= 10) {
                        this.position.x -= 10;
                    }

                } else if (this.position.direction == 'E') {

                    this.position.x -= 1;
                    if (this.position.x < 0) {
                        this.position.x += 10;
                    }

                } else if (this.position.direction == 'S') {
                    this.position.y -= 1;
                    if (this.position.y < 0) {
                        this.position.y += 10;
                    }
                }

            } else if (Objects.equals(command, "r")) {
                if (this.position.direction == 'N') {
                    this.position.direction = 'E';
                } else if (this.position.direction == 'W') {
                    this.position.direction = 'N';
                } else if (this.position.direction == 'E') {
                    this.position.direction = 'S';
                } else if (this.position.direction == 'S') {
                    this.position.direction = 'W';
                }
            } else if (Objects.equals(command, "l")) {
                if (this.position.direction == 'N') {
                    this.position.direction = 'W';
                } else if (this.position.direction == 'W') {
                    this.position.direction = 'S';
                } else if (this.position.direction == 'E') {
                    this.position.direction = 'N';
                } else if (this.position.direction == 'S') {
                    this.position.direction = 'E';
                }
            }

            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.console.print(this.display());
        }
    }
}
