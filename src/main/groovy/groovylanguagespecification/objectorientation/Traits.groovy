package groovylanguagespecification.objectorientation

trait FlyingAbility {
    String fly() { "I'm flying!" }
}

//

class Bird implements FlyingAbility {}
def b = new Bird()
assert b.fly() == "I'm flying!"

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

