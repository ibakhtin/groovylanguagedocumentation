package groovylanguagespecification.operators

// Normal arithmetic operators

assert  1  + 2 == 3
assert  4  - 3 == 1
assert  3  * 5 == 15
assert  3  / 2 == 1.5
assert 10  % 3 == 1
assert  2 ** 3 == 8

// Unary arithmetic operators

assert +3 == 3
assert -4 == 0 - 4

assert -(-1) == 1

//

def a = 2
def b = a++ * 3

assert a == 3 && b == 6

def c = 3
def d = c-- * 2

assert c == 2 && d == 6

def e = 1
def f = ++e + 3

assert e == 2 && f == 5

def g = 4
def h = --g + 1

assert g == 3 && h == 4

// Assignment arithmetic operators

def assignmentA = 4
assignmentA += 3

assert assignmentA == 7

def assignmentB = 5
assignmentB -= 3

assert assignmentB == 2

def assignmentC = 5
assignmentC *= 3

assert assignmentC == 15

def assignmentD = 10
assignmentD /= 2

assert assignmentD == 5

def assignmentE = 10
assignmentE %= 3

assert assignmentE == 1

def assignmentF = 3
assignmentF **= 2

assert assignmentF == 9
