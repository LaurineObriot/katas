# Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

while 1:
    enemy1, dist1, enemy2, dist2 = input(), int(input()), input(), int(input())
    print(enemy1 if dist1 < dist2 else enemy2)
