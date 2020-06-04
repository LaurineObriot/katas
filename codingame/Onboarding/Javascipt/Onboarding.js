/**
 * CodinGame planet is being attacked by slimy insectoid aliens.
**/

// Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

// game loop
while (true) {
    var enemy1 = readline(); // name of enemy 1
    var dist1 = parseInt(readline()); // distance to enemy 1
    var enemy2 = readline(); // name of enemy 2
    var dist2 = parseInt(readline()); // distance to enemy 2
    if (dist1 < dist2) {
        print(enemy1);
    } else {
        print(enemy2);
    }
}
