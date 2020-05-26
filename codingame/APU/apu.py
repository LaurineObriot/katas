width, height, grid = int(input()), int(input()), []
for i in range(height):
    grid.append(list(input()))

for i in range(height):
    for j in range(width):
        if grid[i][j] == '0':
            print("{} {} ".format(j, i), end="")

            jj = 1;
            while j+jj < width and grid[i][j+jj] == '.':
                jj+=1
            if j+jj < width:
                print("{} {} ".format(j+jj, i), end="")
            else:
                print("-1 -1 ", end="")

            ii = 1;
            while i+ii < height and grid[i+ii][j] == '.':
                ii+=1
            if i+ii < height:
                print("{} {} ".format(j, i+ii))
            else:
                print("-1 -1 ")        
