# Game configuration : the differents cards possible and their value
# We would only need to modify this dictionnary if the order of the
# card changed, or if we wanted to play with a 32 card game for example
values = {'2D':2, '2H':2, '2C':2, '2S':2,
          '3D':3, '3H':3, '3C':3, '3S':3,
          '4D':4, '4H':4, '4C':4, '4S':4,
          '5D':5, '5H':5, '5C':5, '5S':5,
          '6D':6, '6H':6, '6C':6, '6S':6,
          '7D':7, '7H':7, '7C':7, '7S':7,
          '8D':8, '8H':8, '8C':8, '8S':8,
          '9D':9, '9H':9, '9C':9, '9S':9,
          '10D':10, '10H':10, '10C':10, '10S':10,
          'JD':11, 'JH':11, 'JC':11, 'JS':11,
          'QD':12, 'QH':12, 'QC':12, 'QS':12,
          'KD':13, 'KH':13, 'KC':13, 'KS':13,
          'AD':14, 'AH':14, 'AC':14, 'AS':14}

def compare(c1, c2):
    ''' returns :
    - 0 if card1 and card 2 have equal values
    - 1 if card1 wins over card2
    - 1 if card2 wins over card1'''
    global values

    if values[c1] < values[c2]:
        return -1
    elif values[c1] > values[c2]:
        return 1
    else:
        return 0


# Reads input to initialize decks
deck1, deck2 = [], []
for i in range(int(input())):
    deck1.append(input())
for i in range(int(input())):
    deck2.append(input())

nbRounds = 0

while True:
    # Each loop is one game round
    # pile1 and pile2 are the cards played this turn by player 1 and 2

    try:
        pile1, pile2 = [deck1.pop(0)], [deck2.pop(0)]
    except IndexError:
        # this error means one of the two decks is empty
        winner = '1' if len(deck1) > 0 else '2'
        print(winner, nbRounds)
        break

    nbRounds+=1

    # In case of war(s)
    try:
        # While there is a war, each player plays 3 other cards
        while compare(pile1[-1], pile2[-1]) == 0:
            for i in range(4):
                pile1.append(deck1.pop(0))
                pile2.append(deck2.pop(0))
    except IndexError:
        # an index error means one of the two decks got empty
        # during a war, and both players are equally first
        print('PAT')
        break

    # Moves the 2 piles to the round winner's deck bottom
    if compare(pile1[-1], pile2[-1]) == -1:
        deck2 = deck2 + pile1 + pile2
    else:
        deck1 = deck1 + pile1 + pile2
