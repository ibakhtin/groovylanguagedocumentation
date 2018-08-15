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

