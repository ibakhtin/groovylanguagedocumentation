package groovylanguagespecification.semantics

import static java.lang.Math.abs
import static java.lang.Math.cos
import static java.lang.Math.sin
import static java.lang.Math.PI

// Closure to type coercion

//// Assigning a closure to a SAM type

////// Functional interfaces

interface Predicate<T> {
    boolean accept(T obj)
}

////// Abstract classes with single abstract method

abstract class Greeter {
    abstract String getName()
    void greet() {
        println "Hello, $name"
    }
}

final Predicate filter = { it.contains 'G' } as Predicate
assert filter.accept('Groovy') == true

Greeter greeter = { 'Groovy' } as Greeter
greeter.greet()

//

Predicate filter02 = { it.contains 'G' }
assert filter02.accept('Groovy') == true

Greeter greeter02 = { 'Groovy' }
greeter02.greet()

//

boolean doFilter(String s) { s.contains('G') }

Predicate filter03 = this.&doFilter
assert filter03.accept('Groovy') == true

Greeter greeter03 = GroovySystem.&getVersion
greeter03.greet()

//// Calling a method accepting a SAM type with a closure

public <T> List<T> filter04(List<T> source, Predicate<T> predicate) {
    source.findAll { predicate.accept(it) }
}

assert filter04(['Java','Groovy'], { it.contains 'G'} as Predicate) == ['Groovy']

assert filter04(['Java','Groovy'], { it.contains 'G'}) == ['Groovy']

//// Closure to arbitrary type coercion

interface FooBar {
    int foo()
    void bar()
}

def impl = { println 'ok'; 123 } as FooBar

assert impl.foo() == 123
impl.bar()

//

class FooBaz {
    int foo() { 1 }
    void baz() { println 'bar' }
}

def implTwo = { println 'ok'; 123 } as FooBaz

assert implTwo.foo() == 123
implTwo.baz()

// Map to type coercion

def map
map = [
        i: 10,
        hasNext: { map.i > 0 },
        next: { map.i-- },
]
def iter = map as Iterator

for (i in iter) { print i + ' ' }

// String to coercion

enum State {
    up,
    down
}

State st = 'up'
assert st == State.up

def val = "up"
State st02 = "${val}"
assert st02 == State.up

//

State switchState(State st) {
    switch (st) {
        case 'up':
            return State.down // explicit constant
        case 'down':
            return 'up' // implicit coercion for return types
    }
}

assert switchState('up' as State) == State.down
assert switchState(State.down) == State.up

// Custom type coercion

class Polar {
    double r
    double phi
//    def asType(Class target) {
//        if (target == Cartesian) {
//            return new Cartesian(x: r * cos(phi), y: r * sin(phi))
//        }
//    }
}

class Cartesian {
    double x
    double y
}

Polar.metaClass.asType = { Class target ->
    if (target == Cartesian) {
        return new Cartesian(x: r * cos(phi), y: r * sin(phi))
    }
}

def sigma = 1E-16
def polar = new Polar(r: 1.0, phi: PI / 2)
def cartesian = polar as Cartesian
assert abs(cartesian.x - sigma) < sigma

// Class literals vs variables and the as operator

//interface GreeterTwo {
//    void greet()
//}
//
//def greeterTwo = { println 'Hello, Groovy!' } as GreeterTwo // Greeter is known statically
//greeterTwo.greet()
//
//Class clazz = Class.forName('GreeterTwo')
//
//greeterTwo = { println 'Hello, Groovy!' }.asType(clazz)
//greeterTwo.greet()