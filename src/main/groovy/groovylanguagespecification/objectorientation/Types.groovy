package groovylanguagespecification.objectorientation

import static groovy.test.GroovyAssert.shouldFail

// Primitive types wrappers

// boolean  Boolean
// char     Character
// short    Short
// int      Integer
// long     Long
// float    Float
// double   Double

//

class Foo {
    static int i
}

assert Foo.class.getDeclaredField('i').type == int.class
assert Foo.i.class != int.class && Foo.i.class == Integer.class

// Class

class Person {

    String name
    Integer age

    def increaseAge(Integer years) {
        this.age += years
    }
}

// Normal class

def p = new Person()

// Inner class

class Outer {
    private String privateStr

    def callInnerMethod() {
        new Inner().methodA()
    }

    class Inner {
        def methodA() {
            println "${privateStr}."
        }
    }
}

class Outer2 {
    private String privateStr = 'some string'

    def startThread() {
        new Thread(new Inner2()).start()
    }

    class Inner2 implements Runnable {
        void run() {
            println "${privateStr}."
        }
    }
}

class Outer3 {
    private String privateStr = 'some string'

    def startThread() {
        new Thread(new Runnable() {
            void run() {
                println "${privateStr}."
            }
        }).start()
    }
}

// Abstract class

abstract class Abstract {
    String name

    abstract def abstractMethod()

    def concreteMethod() {
        println 'concrete'
    }
}

// Interface

interface Greeter {
    void greet(String name)
}

//

class SystemGreeter implements Greeter {
    @Override
    void greet(String name) {
        println "Hello $name"
    }
}

def greeter = new SystemGreeter()
assert greeter instanceof Greeter

//

interface ExtendedGreeter extends Greeter {
    void sayBye(String name)
}

//

class DefaultGreeter {
    void greet(String name) { println "Hello" }
}

greeter = new DefaultGreeter()
assert !(greeter instanceof Greeter)

//

greeter = new DefaultGreeter()
coerced = greeter as Greeter
assert coerced instanceof Greeter

// Constructors

// Positional argument constructor

class PersonConstructor {
    String name
    Integer age

    PersonConstructor(name, age) {
        this.name = name
        this.age = age
    }
}

def person1 = new PersonConstructor('Marie', 1)
def person2 = ['Marie', 2] as PersonConstructor
PersonConstructor person3 = ['Marie', 3]

assert person1.name == 'Marie' && person1.age == 1
assert person2.name == 'Marie' && person2.age == 2
assert person3.name == 'Marie' && person3.age == 3

// Named argument constructor

class PersonWOConstructor {
    String name
    Integer age
}

def person4 = new PersonWOConstructor()
def person5 = new PersonWOConstructor(name: 'Marie')
def person6 = new PersonWOConstructor(age: 1)
def person7 = new PersonWOConstructor(name: 'Marie', age: 2)

assert person4.name == null && person4.age == null
assert person5.name == 'Marie' && person5.age == null
assert person6.name == null && person6.age == 1
assert person7.name == 'Marie' && person7.age == 2

// Methods

// Methods definition

def someMethod() { 'method called' }
String anotherMethod() { 'another method called' }
def thirdMethod(param1) { "$param1 passed" }
static String fourthMethod(String param1) { "$param1 passed" }

// Named arguments

def foo1(Map args) { "${args.name}: ${args.age}" }
assert foo1(name: 'Marie', age: 1) == 'Marie: 1'

// Default arguments

def foo2(String part1, Integer part2 = 1) { [name: part1, age: part2] }
assert foo2('Marie').age == 1

// Varargs

def foo3(Object... args) { args.length }
assert foo3() == 0
assert foo3(1) == 1
assert foo3(1, 2) == 2

//

def foo4(Object[] args) { args.length }
assert foo4() == 0
assert foo4(1) == 1
assert foo4(1, 2) == 2

//

def foo5(Object... args) { args }
assert foo5(null) == null

//

def foo6(Object... args) { args }
Integer[] ints = [1, 2]
assert foo6(ints) == [1, 2]

//

def foo7(Object... args) { 1 }
def foo7(Object x) { 2 }
assert foo7() == 1
assert foo7(1) == 2
assert foo7(1, 2) == 1

// Exception declaration

def badRead() {
    new File('doesNotExist.txt').text
}

shouldFail(FileNotFoundException) {
    badRead()
}

//

def badRead2() throws FileNotFoundException {
    new File('doesNotExist.txt').text
}

shouldFail(FileNotFoundException) {
    badRead2()
}

// Field and properties

// Fields

