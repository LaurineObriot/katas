import sys, math

import sys
sys.setrecursionlimit(10000)

class Node:
    ''' represents a node of a patricia tree'''
    def __init__(self, char=' '):
    self.char, self.sons, self.word, self.mem = char, dict(), False, dict()

    def memoize(self, index, value):
    self.mem[index] = value

class Patricia:
    ''' represents a patricia tree'''

    def __init__(self):
        self.root = Node()

    def add(self, word):
        it = self.root
        for c in word:
            if c not in it.sons:
                it.sons[c] = Node(c)
                it = it.sons[c]
                it.word = True

MAX_WORD_LENGTH = 20

abc = {'.-':'A', '-...':'B', '-.-.':'C', '-..':'D', '.':'E',
 '..-.':'F', '--.':'G', '....':'H', '..':'I', '.---':'J',
 '-.-':'K', '.-..':'L', '--':'M', '-.':'N', '---':'O',
 '.--.':'P', '--.-':'Q', '.-.':'R', '...':'S', '-':'T',
 '..-':'U', '...-':'V', '.--':'W', '-..-':'X', '-.--':'Y',
 '--..':'Z'}

msg = input()

dictionary = Patricia()
for i in range(int(input())):
    dictionary.add(input())

def possibleLetters(index):
    ''' returns a list of pair (letter, length of the corresponding morse code) containing
    all the letters it is possible to read in msg at index'''
    global abc, msg
    return [(abc[morse], len(morse)) for morse in abc.keys() if msg.startswith(morse, index)]

def nbMsg(index, decodedYet):
    global msg, dictionary, MAX_WORD_LENGTH
    # if the size of decodedYet exceeds the maximum length of a word,
    # then the previous decyphering does not lead to a correct msg, returns 0
    res = 0
    if index in decodedYet.mem:
        return decodedYet.mem[index]
    else:
        if index == len(msg) and decodedYet.word:
            res = 1
        else:
            nextLetters = possibleLetters(index)
            # nb of msg possible if we continue to read
            for l, offset in nextLetters:
                if l in decodedYet.sons:
                    res += nbMsg(index+offset, decodedYet.sons[l])

            if decodedYet.word:
                # nb of msg if we move to next word
                res += nbMsg(index, dictionary.root)

        decodedYet.memoize(index, res)
        return res

print(nbMsg(0, dictionary.root))
