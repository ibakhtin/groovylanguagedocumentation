package groovylanguagespecification.operators

// Spread operator

class Car {
    String make
    String model
}

def cars = [
    new Car(make: 'Peugeot', model: '508'),
    new Car(make: 'Renault', model: 'Clio')
]

def makes = cars*.make
assert makes == ['Peugeot', 'Renault']

//

cars = [
    new Car(make: 'Peugeot', model: '508'),
    null,
    new Car(make: 'Renault', model: 'Clio')
]

assert cars*.make == ['Peugeot', null, 'Renault']
assert null*.make == null

//

class Component {
    Long id
    String name
}

class CompositeObject implements Iterable<Component> {
    def components = [
        new Component(id: 1, name: 'Foo'),
        new Component(id: 2, name: 'Bar')
    ]

    @Override
    Iterator<Component> iterator() {
        components.iterator()
    }
}

def composite = new CompositeObject()
assert composite*.id == [1,2]
assert composite*.name == ['Foo','Bar']

// Spreading method arguments

int function(int x, int y, int z) {
    x * y + z
}

def args = [4, 5, 6]

assert function(*args) == 26

args = [4]
assert function(*args, 5, 6) == 26

// Spread list elements

def items = [4, 5]
def list = [1, 2, 3, *items, 6]
assert list == [1, 2, 3, 4, 5, 6]

// Spread map elements

def m1 = [c: 3, d: 4]
def map = [a: 1, b: 2, *:m1]
assert map == [a: 1, b: 2, c: 3, d: 4]

//

m1 = [c:3, d:4]
map = [a:1, b:2, *:m1, d: 8]
assert map == [a:1, b:2, c:3, d:8]

// Range operator

def range = 0..5
assert (0..5).collect() == [0, 1, 2, 3, 4, 5]
assert (0..<5).collect() == [0, 1, 2, 3, 4]
assert (0..5) instanceof List
assert (0..5) instanceof IntRange
assert (0..5).size() == 6

//

assert ('a'..'d').collect() == ['a','b','c','d']

// Spaceship operator

assert (1 <=> 1) == 0
assert (1 <=> 2) == -1
assert (2 <=> 1) == 1
assert ('a' <=> 'z') == -1

// Subscript operator

def listTwo = [0,1,2,3,4]
assert listTwo[2] == 2
listTwo[2] = 4
assert listTwo[0..2] == [0,1,4]
listTwo[0..2] = [6,6,6]
assert listTwo == [6,6,6,3,4]

//

class User02 {
    Long id
    String name

    def getAt(int i) {
        switch (i) {
            case 0: return id
            case 1: return name
        }
        throw new IllegalArgumentException("No such element $i")
    }

    void putAt(int i, def value) {
        switch (i) {
            case 0: id = value; return
            case 1: name = value; return
        }
        throw new IllegalArgumentException("No such element $i")
    }
}

def user = new User02(id: 1, name: 'Alex')
assert user[0] == 1
assert user[1] == 'Alex'
user[1] = 'Bob'
assert user.name == 'Bob'

// Membership operator

def names = ['Grace', 'Rod', 'Emmi']
assert 'Emmi' in names
assert names.contains('Emmi')
assert names.isCase('Emmi')

// Identity operator

def list1 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
def list2 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
assert list1 == list2
assert !list1.is(list2)

// Coercion operator

Integer x1 = 123
String s1 = (String) x1

assert s1 == '123'

//

Integer x2 = 123
String s2 = x2 as String

assert s2 == '123'

// Diamond operator

List<String> strings = new LinkedList<>()

assert strings instanceof LinkedList<String>

// Call operator

class MyCallable {
    int call(int x) {
        2 * x
    }
}

def mc = new MyCallable()
assert mc.call(2) == 4
assert mc(2) == 4

