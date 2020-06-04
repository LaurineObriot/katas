# String multiplication in Python as particularly handy for this puzzle where the string "0" is printed a lot. Python's advanced formatting function allows to convert easily a character into its binary ascii code.

msg = input()
binMsg = []
for c in msg:
    # converts c to a 7digit 0 left padded binary code
    binMsg.append('{0:07b}'.format(ord(c)))

# reunites the list of string into one string and browses it, updating :
# - prev which contains the last character of binMsg read : '0' or '1'
# - count which countains the number of consecutive identical characters
# and printing the result gradually
prev, count = None, 0
for c in ''.join(binMsg):
    if c != prev:
        print(('0' * count) + \
        (' ' if count else '') + \
        ("00 " if c == '0' else "0 "), end='')
        count = 1
    else:
        count+=1
    prev = c
print("0" * count, end="")
