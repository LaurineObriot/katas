while 1:
    enemy1, dist1, enemy2, dist2 = input(), int(input()), input(), int(input())
    print(enemy1 if dist1 < dist2 else enemy2)
