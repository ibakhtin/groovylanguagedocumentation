package groovylanguagespecification.closures

def x = 1
def gs = "x = ${x}"
assert gs == 'x = 1'

x = 2
assert gs == 'x = 1'

//

def x2 = 1
def gs2 = "x2 = ${-> x2}"
assert gs2 == 'x2 = 1'

x2 = 2
assert gs2 == 'x2 = 2'

class Person1 {
    String name
    String toString() { name }
}

def sam = new Person1(name: 'Sam')
def lucy = new Person1(name: 'Lucy')
def p = sam
def gs3 = "Name: ${p}"
assert gs3 == 'Name: Sam'
p = lucy
assert gs3 == 'Name: Sam'
sam.name = 'Lucy'
assert gs3 == 'Name: Lucy'