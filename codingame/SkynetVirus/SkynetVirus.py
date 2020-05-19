import sys, math

class Node(object):
    '''Class representing a node of the network, each node as :
    a unique id, constant
    a list of neighbour nodes'''
    def __init__(self, n =0):
        self.n = n
        self.neighb = []

    def addNeighb(self, m):
        self.neighb.append(m)

    def removeNeighb(self, m):
        self.neighb.remove(m)

class Path(object):
    '''class representing a path in the network, by a list of node'''
    def __init__(self, begin, end, nodes):
        '''This constructor builds a shortest path in the network described by
        nodes, between begin and end'''
        self.p = []
        N = len(nodes)

        # explored[i] is True <=> node i has been visited
        # prec[i] = j <=> node i has been visited coming from node j
        explored, prec = [], []
        for i in range(N):
            explored.append(False)
            prec.append(-1)
        explored[begin.n] = True

        # A BFS is performed starting from node begin, until finding node end
        q, n = [], None
        q.append(begin)
        while len(q) != 0:
            n = q.pop()
            if n == end:
                break
            else:
                for m in n.neighb:
                    if not explored[m.n]:
                        explored[m.n] = True
                        prec[m.n] = n
                        q[0:0] = [m]
        # if the queue is empty before finding end, there is no path
        # else a shortest path is identified by backtracking
        if n == end:
            while n != begin:
                self.p[0:0] = [n]
                n = prec[n.n]
            self.p[0:0] = [n]

    def length(self):
        return len(self.p)

    def addFirst(self, n):
        self.p[0:0] = [n]

    def getNode(self, i):
        return self.p[i]

def cut(n1, n2):
    '''removes the edge between node n1 and n2'''
    n1.removeNeighb(n2)
    n2.removeNeighb(n1)
    print("{} {}".format(n1.n, n2.n))

# number of nodes, links and exits
N, L, E = [int(i) for i in input().split()]

# initializing the array of nodes and linking them
nodes = []
for i in range(N):
    nodes.append(Node(i))
for i in range(L):
    N1, N2 = [int(j) for j in input().split()]
    nodes[N1].addNeighb(nodes[N2])
    nodes[N2].addNeighb(nodes[N1])

# initializing the array of exits
exits = []
for i in range(E):
    exits.append(nodes[int(input())])

while 1:
    # each turn, identifies one of the shortest paths from Skynet Agent
    # to each exit node (by a Breadth First Search), then cuts the shortest
    SI = int(input())
    shortestPaths = []
    minLength = 9999999
    pathToCut = None

    for i in range(E):
        shortestPaths.append(Path(nodes[SI], exits[i], nodes))
        length = shortestPaths[i].length()
        if (length != 0) and (length < minLength):
            minLength = length
            pathToCut = shortestPaths[i]

    cut(pathToCut.getNode(0), pathToCut.getNode(1))
