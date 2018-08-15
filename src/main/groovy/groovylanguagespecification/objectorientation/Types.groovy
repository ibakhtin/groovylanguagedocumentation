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

class Data {
    private int id
    protected String description
    public static final boolean DEBUG = false
}

//

class BadPractice {
    private mapping
}
class GoodPractice {
    private Map<String,String> mapping
}

// Properties

// Groovy will then generate the getters/setters appropriately. For example:
class Person2 {
    String name
    int age
}

// If a property is declared final, no setter is generated:
class Person3 {
    final String name
    final int age
    Person3(String name, int age) {
        this.name = name
        this.age = age
    }
}

// Properties are accessed by name and will call the getter or setter
// transparently, unless the code is in the class which defines the property:
class Person4 {
    String name
    void name(String name) {
        this.name = "Wonder$name"
    }
    String wonder() {
        this.name
    }
}

def p4 = new Person4()
p4.name = 'Marge'
assert p4.name == 'Marge'
p4.name('Marge')
assert p4.wonder() == 'WonderMarge'

//

class Person5 {
    String name
    int age
}
def p5 = new Person5()
assert p5.properties.keySet().containsAll(['name','age'])

//

class PseudoProperties {
    // a pseudo property "name"
    void setName(String name) {}
    String getName() {}

    // a pseudo read-only property "age"
    int getAge() { 42 }

    // a pseudo write-only property "groovy"
    void setGroovy(boolean groovy) {  }
}
def pp = new PseudoProperties()
pp.name = 'Foo'
assert pp.age == 42
pp.groovy = true

// Annotation

// Annotation definition

@interface SomeAnnotation1 {}

//

@interface SomeAnnotation2 {
    String value()
}
@interface SomeAnnotation3 {
    String value() default 'something'
}
@interface SomeAnnotation4 {
    int step()
}
@interface SomeAnnotation5 {
    Class appliesTo()
}
@interface SomeAnnotation6 {}
@interface SomeAnnotations {
    SomeAnnotation6[] value()
}
enum DayOfWeek { mon, tue, wed, thu, fri, sat, sun }
@interface Scheduled {
    DayOfWeek dayOfWeek()
}

// Annotation placement

import java.lang.annotation.ElementType
import java.lang.annotation.Target

@Target([ElementType.METHOD, ElementType.TYPE])
@interface SomeAnnotation7 {}

// Annotation member values

@interface Responce {
    int statusCode()
}

@Responce(statusCode = 404)
void someFunction() {
    // ...
}

@interface Page {
    String value()
    int statusCode() default 200
}

@Page(value='/home')
void home() {
    // ...
}

@Page('/users')
void userList() {
    // ...
}

@Page(value='error',statusCode=404)
void notFound() {
    // ...
}

// Retention policy

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
@interface SomeAnnotation8 {}

// Closure annotation parameters

@Retention(RetentionPolicy.RUNTIME)
@interface OnlyIf {
    Class value()
}

class Tasks {
    Set result = []
    void alwaysExecuted() {
        result << 1
    }
    @OnlyIf({ jdk>=6 })
    void supportedOnlyInJDK6() {
        result << 'JDK 6'
    }
    @OnlyIf({ jdk>=7 && windows })
    void requiresJDK7AndWindows() {
        result << 'JDK 7 Windows'
    }
}

// TODO
// Add Meta-annotations

//