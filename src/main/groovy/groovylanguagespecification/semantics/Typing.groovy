package groovylanguagespecification.semantics

// Optional typing

String string01 = 'foo'
assert string01.toUpperCase()


def string02 = 'foo'
assert string02.toUpperCase()

//

String concat01(String a, String b) {
    a + b
}

assert concat01('foo','bar') == 'foobar'


def concat02(def a, def b) {
    a + b
}

assert concat02('foo','bar') == 'foobar'
assert concat02(1,2) == 3

private concat03(a, b) {
    a + b
}
assert concat03('foo','bar') == 'foobar'
assert concat03(1,2) == 3

// Static type checking

class Person01 {
    String firstName
    String lastName
}

Person01.metaClass.getFormattedName = { "$delegate.firstName $delegate.lastName" }
def person01 = new Person01(firstName: 'Raymond', lastName: 'Devos')
assert person01.formattedName == 'Raymond Devos'

//

