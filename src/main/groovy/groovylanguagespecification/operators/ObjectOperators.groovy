package groovylanguagespecification.operators

import groovy.transform.Canonical

// Safe navigation operator

def person = null
def name = person?.name
assert name == null

// Direct field access operator

class UserTwo {
    public final String name
    UserTwo(String name) { this.name = name }
    String getName() { "Name: ${this.name}" }
}

def user = new UserTwo('Bob')
assert user.name == 'Name: Bob'

assert user.@name == 'Bob'

// Method pointer operator

def string = 'example of method reference'
def func = string.&toUpperCase
def upperCaseString = func()
assert upperCaseString == string.toUpperCase()

//

@Canonical
class Person {
    String name
    Integer age
}

def transform(List elements, Closure action) {
    def result = []
    elements.each {
        result << action(it)
    }
    result
}
String describe(Person p) {
    "$p.name is $p.age"
}
def action = this.&describe
def list = [
        new Person(name: 'Bob',   age: 42),
        new Person(name: 'Julia', age: 35)
]
assert transform(list, action) == ['Bob is 42', 'Julia is 35']

//

def doSomething(String str) { str.toUpperCase() }
def doSomething(Integer x) { 2 * x }
def reference = this.&doSomething
assert reference('foo') == 'FOO'
assert reference(123)   == 246