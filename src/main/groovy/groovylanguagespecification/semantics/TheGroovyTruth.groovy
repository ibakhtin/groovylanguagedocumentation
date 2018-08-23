package groovylanguagespecification.semantics

// Boolean expressions

assert true
assert !false

// Collections and Arrays

assert [1, 2, 3]
assert ![]

// Matchers

assert ('a' =~ /a/)
assert !('a' =~ /b/)

// Iterators and Enumerations

assert [0].iterator()
assert ![].iterator()
Vector v = [0] as Vector
Enumeration enumeration = v.elements()
assert enumeration
enumeration.nextElement()
assert !enumeration

// Maps

assert ['one' : 1]
assert ![:]

// Strings

assert 'a'
assert !''
def nonEmpty = 'a'
assert "$nonEmpty"
def empty = ''
assert !"$empty"

// Numbers

assert 1
assert 3.5
assert !0

// Object references

assert new Object()
assert !null

// Customizing the truth with asBoolean() methods

class Color {
    String name

    boolean asBoolean(){
        name == 'green' ? true : false
    }
}

assert new Color(name: 'green')
assert !new Color(name: 'red')