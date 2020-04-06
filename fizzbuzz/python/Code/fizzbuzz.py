for x in xrange(1, 200):

    fizz = not x % 3
    buzz = not x % 5

    if fizz and buzz :
        print "FizzBuzz"
    elif fizz:
        print "Fizz"
    elif buzz:
        print "Buzz"
    else:
        print x

# 2 autres manières de faire en 1 ligne :

	# for x in xrange(1, 200): print "Fizz"[x % 3 * 4:] + "Buzz" [x % 5 * 4:] or x

	# print '\n'.join('Fizz' * (not i % 3) + 'Buzz' * (not i % 5) or str(i) for i in xrange(1, 200))
