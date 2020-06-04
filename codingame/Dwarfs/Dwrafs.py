class Person(object):
    '''Represents a person, which has a unique id, a depth in the tree of relations
       and a list of persons she influenced'''
    def __init__(self, id):
        self.id, self.depth, self.influenced = id, 1, []

    def add(self, p):
        '''add person p amongst the person influenced'''
        self.influenced.append(p)

    def updateDepths(self):
        '''update depths of the persons influenced, according to self depth'''
        for p in self.influenced:
            if (p.depth < self.depth+1):
                p.depth = self.depth+1
                p.updateDepths()

# number of relations
N = int(input())
persons = {}
for i in range(N):
    x, y = [int(j) for j in input().split()]
    if not x in persons:
        persons[x] = Person(x)
    if not y in persons:
        persons[y] = Person(y)
    px, py = persons[x], persons[y]
    px.add(py)
    px.updateDepths()

maximum = 0
for p in persons.values():
    maximum = max(p.depth, maximum)

print(maximum)
