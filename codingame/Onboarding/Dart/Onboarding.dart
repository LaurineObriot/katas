import 'dart:io';
import 'dart:math';

/**
 * CodinGame planet is being attacked by slimy insectoid aliens.
**/

// Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

void main() {

    // game loop
    while (true) {
        String enemy1 = stdin.readLineSync(); // name of enemy 1
        int dist1 = int.parse(stdin.readLineSync()); // distance to enemy 1
        String enemy2 = stdin.readLineSync(); // name of enemy 2
        int dist2 = int.parse(stdin.readLineSync()); // distance to enemy 2
        if ( dist1 < dist2 ) {
             print(enemy1);
        } else {
             print(enemy2);
        }
    }
}
