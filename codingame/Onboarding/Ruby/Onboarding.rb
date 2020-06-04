STDOUT.sync = true # DO NOT REMOVE
# CodinGame planet is being attacked by slimy insectoid aliens.

# Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

# game loop
loop do
    $enemy1 = gets.chomp # name of enemy 1
    $dist1 = gets.to_i # distance to enemy 1
    $enemy2 = gets.chomp # name of enemy 2
    $dist2 = gets.to_i # distance to enemy 2

    if $dist1 < $dist2
        puts $enemy1
    else
        puts $enemy2
    end

end
