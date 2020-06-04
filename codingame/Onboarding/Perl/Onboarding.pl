select(STDOUT); $| = 1; # DO NOT REMOVE

# CodinGame planet is being attacked by slimy insectoid aliens.

# Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

# game loop
while (1) {
    chomp($enemy1 = <STDIN>); # name of enemy 1
    chomp($dist1 = <STDIN>); # distance to enemy 1
    chomp($enemy2 = <STDIN>); # name of enemy 2
    chomp($dist2 = <STDIN>); # distance to enemy 2
    if ($dist1 < $dist2) {
        print "$enemy1\n";
    } else{
        print "$enemy2\n";
    }
}
