package groovylanguagespecification.objectorientation

import static groovy.test.GroovyAssert.shouldFail

trait FlyingAbilityOne {
    String fly() { "I'm flying!" }
}

//

class Bird implements FlyingAbilityOne {}
def bird = new Bird()
assert bird.fly() == "I'm flying!"

// Methods

// Public methods

trait SwimmingAbility {
    String swim() { "I'm swimming!" }
}

// Abstract methods

trait Greetable {
    abstract String name()
    String greeting() { "Hello, ${name()}!" }
}

//

class Person9 implements Greetable {
    String name() { 'Bob' }
}

def person9 = new Person9()
assert person9.greeting() == 'Hello, Bob!'

// Private methods

trait Greeter2 {
    private String greetingMessage() {
        'Hello from a private method!'
    }
    String greet() {
        def message = greetingMessage()
        println message
        message
    }
}

class GreetingMachine implements Greeter2 {}
def greetingMachine = new GreetingMachine()

assert greetingMachine.greet() == "Hello from a private method!"
try {
    assert greetingMachine.greetingMessage()
} catch (MissingMethodException e) {
    println "greetingMessage is private in trait"
}

// The meaning of this

trait Introspector {
    def whoAmI() { this }
}

class Foo2 implements Introspector {}
def foo2 = new Foo2()
assert foo2.whoAmI().is(foo2)

// Interfaces

interface Named {
    String name()
}
trait Greetable2 implements Named {
    String greeting() { "Hello, ${name()}!" }
}
class Person8 implements Greetable2 {
    String name() { 'Bob' }
}

def person8 = new Person8()
assert person8.greeting() == 'Hello, Bob!'
assert person8 instanceof Named
assert person8 instanceof Greetable2

// Properties

trait Named2 {
    String name
}

class Person10 implements Named2 {}

def person10 = new Person10(name: 'Bob')

assert person10.name == 'Bob'
assert person10.getName() == 'Bob'

// Fields

trait Counter {
    private int count = 0
    int count() { count += 1; count }
}

class Bar implements Counter {}

def bar = new Bar()

assert bar.count() == 1
assert bar.count() == 2

// Public fields

trait Named3 {
    public String name
}
class Person11 implements Named3 {}
def person11 = new Person11()
// person11.Named3__name = 'Bob'

// Composition of behaviors

trait FlyingAbility {
    String fly() { "I'm flying!" }
}
trait SpeakingAbility {
    String speak() { "I'm speaking!" }
}

class DuckOne implements FlyingAbility, SpeakingAbility {}

def duckOne = new DuckOne()
assert duckOne.fly() == "I'm flying!"
assert duckOne.speak() == "I'm speaking!"

// Overriding default methods

class DuckTwo implements FlyingAbility, SpeakingAbility {
    String quack() { "Quack!" }
    String speak() { quack() }
}

def duckTwo = new DuckTwo()
assert duckTwo.fly() == "I'm flying!"
assert duckTwo.quack() == "Quack!"
assert duckTwo.speak() == "Quack!"

// Extending traits

// Simple inheritance

trait NamedOne {
    String name
}
trait Polite extends NamedOne {
    String introduce() { "Hello, I am $name" }
}
class PolitePerson implements Polite {}
def politePerson = new PolitePerson(name: 'Alice')
assert politePerson.introduce() == 'Hello, I am Alice'

// Multiple inheritance

trait WithId {
    Long id
}
trait WithName {
    String name
}
trait Identified implements WithId, WithName {}

class IdentifiedPerson implements Identified {}

def identifiedPerson = new IdentifiedPerson(id: 100, name: 'Billy')
assert identifiedPerson.id == 100
assert identifiedPerson.name == 'Billy'

// Duck typing and traits

// Dynamic code

trait SpeakingDuck {
    String speak() { quack() }
}
class DuckThree implements SpeakingDuck {
    String methodMissing(String name, args) {
        "${name.capitalize()}!"
    }
}

def duckThree = new DuckThree()
assert duckThree.speak() == 'Quack!'

// Dynamic methods in a tait

trait DynamicObject {
    private Map props = [:]
    def methodMissing(String name, args) {
        name.toUpperCase()
    }
    def propertyMissing(String prop) {
        props[prop]
    }
    void setProperty(String prop, Object value) {
        props[prop] = value
    }
}

class Dynamic implements DynamicObject {
    String existingProperty = 'ok'
    String existingMethod() { 'ok' }
}
def dynamic = new Dynamic()
assert dynamic.existingProperty == 'ok'
assert dynamic.dynnamicProperty == null
dynamic.dynnamicProperty = 'bar'
assert dynamic.dynnamicProperty == 'bar'
assert dynamic.existingMethod() == 'ok'
assert dynamic.someMethod() == 'SOMEMETHOD'

// MULTIPLE INHERITANCE CONFLICTS

// Default conflict resolution

trait A {
    String exec() { 'A' }
}
trait B {
    String exec() { 'B' }
}

class C implements A, B {}

def c = new C()
assert c.exec() == 'B'

// User conflict resolution

class D implements A, B {
    String exec() { A.super.exec() }
}
def d = new D()
assert d.exec() == 'A'

// RUNTIME IMPLEMENTATION OF TRAITS

// Implementing a trait at runtime

trait Extra {
    String extra() { "I'm an extra method" }
}
class Something {
    String doSomething() { 'Something' }
}

def somethingAsExtra = new Something() as Extra

assert somethingAsExtra.extra() == "I'm an extra method"
assert somethingAsExtra.doSomething() == 'Something'

// Implementing multiple traits at once

trait A1 { String methodFromA1() { 'A1' } }
trait B1 { String methodFromB1() { 'B1' } }

class C1 {}

def c1 = new C1()

shouldFail MissingMethodException, { c1.methodFromA() }
shouldFail MissingMethodException, { c1.methodFromB() }

def d1 = c1.withTraits A1, B1

assert d1.methodFromA1() == 'A1'
assert d1.methodFromB1() == 'B1'


// CHAINING BEHAVIOR

interface MessageHandler {
    void on(String message, Map payload)
}

trait DefaultHandler implements MessageHandler {
    void on(String message, Map payload) {
        println "Received $message with payload $payload"
    }
}

class SimpleHandler implements DefaultHandler {}

class SimpleHandlerWithLogging implements DefaultHandler {
    void on(String message, Map payload) {
        println "Seeing $message with payload $payload"
        DefaultHandler.super.on(message, payload)
    }
}

