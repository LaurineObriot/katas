input = new Scanner(System.in);

/**
 * CodinGame planet is being attacked by slimy insectoid aliens.
**/

//Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

// game loop
while (true) {
    enemy1 = input.next() // name of enemy 1
    dist1 = input.nextInt() // distance to enemy 1
    enemy2 = input.next() // name of enemy 2
    dist2 = input.nextInt() // distance to enemy 2

    if ( dist1 < dist2 )
    println enemy1
    else
    println enemy2

}
