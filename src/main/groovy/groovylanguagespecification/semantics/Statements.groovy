package groovylanguagespecification.semantics

import jdk.nashorn.internal.ir.annotations.Immutable

// Variable definition

String x
def o

// Variable assignment

x = 1
println x

x = new java.util.Date()
println x

x = -3.1499392
println x

x = false
println x

x = "Hi"
println x

// Multiple assignment

def (a, b, c) = [10, 20, 'foo']
assert a == 10 && b == 20 && c == 'foo'

//

def (int i, String j) = [10, 'foo']
assert i == 10 && j == 'foo'

//

def num = [1, 3, 5]
def a2, b2, c2
(a2, b2, c2) = num
assert a2 == 1 && b2 == 3 && c2 == 5

def (_, month, year) = "18th June 2009".split()
assert "In $month of $year" == 'In June of 2009'

// Overflow and Underflow

def (a3, b3, c3) = [1, 2]
assert a3 == 1 && b3 == 2 && c3 == null

//

def (a4, b4) = [1, 2, 3]
assert a4 == 1 && b4 == 2

// Object destructuring with multiple assignment

@Immutable
class Coordinates {
    double latitude
    double longitude

    double getAt(int idx) {
        if (idx == 0) latitude
        else if (idx == 1) longitude
        else throw new Exception("Wrong coordinate index, use 0 or 1")
    }
}

def coordinates = new Coordinates(latitude: 43.23, longitude: 3.67)

def (la, lo) = coordinates

assert la == 43.23
assert lo == 3.67

// Control structures

// Conditional structures

// if / else

def x5 = false
def y5 = false

if ( !x5 ) {
    x5 = true
}

assert x5 == true

if (x5) {
    x5 = false
} else {
    y5 = true
}

assert x5 == y5

// switch / case

def x6 = 12.3
def result6 = ''

switch (x6) {
    case 'foo':
        result6 = 'foo'
        break

    case 'bar':
        result6 = 'bar'
        break

    case [4, 5, 6, 'inList']:
        result6 = 'list'
        break

    case 12..36:
        result6 = 'range'
        break

    case Integer:
        result6 = 'integer'
        break

    case Number:
        result6 = 'number'
        break

    case { it < 0 }:
        result6 = 'negative'
        break

    default:
        result6 = 'default'
}

assert result6 == 'number'

// Looping structures

// Classic for loop

String message = ''

for (int i1 = 0; i1 < 5; i1++) {
    message += 'Hi '
}

assert message == 'Hi Hi Hi Hi Hi '

// for in loop

// iterate over range
def x7 = 0
for (i7 in 0..9) {
    x7 += i7
}
assert x7 == 45

// iterate over a list
acc8 = 0
for (i8 in [0, 1, 2, 3, 4]) {
    acc8 += i8
}
assert acc8 == 10

// iterate over array
def array9 = (0..4).toArray()
def acc9 = 0
for (i9 in array9) {
    acc9 += i9
}
assert acc9 == 10

// iterate over a map
def map10 = ['abc': 1, 'def': 2, 'ghi': 3]
acc10 = 0
for (e10 in map10) {
    acc10 += e10.value
}
assert acc10 == 6

// iterate over the characters in a string
def text = "abc"
def list = []
for (ch in text) list << ch
assert list == ['a', 'b', 'c']

// while loop

def x11 = 0
def y11 = 5

while ( y11-- > 0 ) {
    x11++
}

assert x11 == 5

// Exception handling

// try / catch / finally

try {
    'moo'.toLong()
    assert false
} catch ( e ) {
    assert e in NumberFormatException
}

//

def z12
try {
    def i12 = 7, j12 = 0
    try {
        def k12 = i12 / j12
        assert false        //never reached due to Exception in previous line
    } finally {
        z12 = 'reached here'  //always executed even if Exception thrown
    }
} catch ( e12 ) {
    assert e12 in ArithmeticException
    assert z12 == 'reached here'
}

// Power assertion

def x13 = 2
def y13 = 7
def z13 = 5
def calc = { a13, b13 -> a13 * b13 + 1 }
assert !(calc(x13,y13) == [x13,z13].sum())

//

def x14 = 2
def y14 = 7
def z14 = 5
def calc14 = { a14, b14 -> a14 * b14 + 1 }
//assert calc14(x14,y14) == z14 * z14 : 'Incorrect computation result'

// Labeled statements

given:
    def x15 = 1
    def y15 = 2
when:
    def z15 = x15 + y15
then:
    assert z15 == 3
