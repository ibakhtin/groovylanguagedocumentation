package groovylanguagespecification.closures

// OWNER, DELEGATE AND THIS

// this -- corresponds to the enclosing class where the closure is defined

// owner -- corresponds to the enclosing object where the closure is defined,
// which may be either a class or a closure

// delegate -- corresponds to a third party object where methods calls or
// properties are resolved whenever the receiver of the message is not defined

//// The meaning of this

class ThisDemoEnclosing {
    void run() {
        def whatIsThisObject = { getThisObject() } // 1
        assert whatIsThisObject() == this // 2
        def whatIsThis = { this } // 3
        assert whatIsThis() == this // 4
    }
}

new ThisDemoEnclosing().run()

class ThisDemoEnclosedInInnerClass {
    class Inner {
        Closure cl = { this } // 5
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner // 6
    }
}

new ThisDemoEnclosedInInnerClass().run()

class ThisDemoNestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { this } // 7
            cl()
        }
        assert nestedClosures() == this // 8
    }
}

new ThisDemoNestedClosures().run()

//

class ThisDemoPerson {
    String name
    int age
    String toString() { "$name is $age years old" }

    String dump() {
        def cl = {
            String msg = this.toString()
            println msg
            msg
        }
        cl()
    }
}

def person = new ThisDemoPerson(name:'Janice', age:74)
assert person.dump() == 'Janice is 74 years old'

// Owner of closure

class OwnerDemoEnclosing {
    void run() {
        def whatIsOwnerMethod = { getOwner() }
        assert whatIsOwnerMethod() == this
        def whatIsOwner = { owner }
        assert whatIsOwner() == this
    }
}

new OwnerDemoEnclosing().run()

class OwnerDemoEnclosedInInnerClass {
    class Inner {
        Closure cl = { owner }
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner
    }
}

new OwnerDemoEnclosedInInnerClass().run()

class OwnerDemoNestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { owner }
            cl()
        }
        assert nestedClosures() == nestedClosures
    }
}

new OwnerDemoNestedClosures().run()

// Delegate of a closure

class DelegateDemoEnclosing {
    void run() {
        def cl1 = { getDelegate() }
        def cl2 = { delegate }
        assert cl1() == cl2()
        assert cl1() == this
        def enclosed = {
            { -> delegate }.call()
        }
        assert enclosed() == enclosed
    }
}

class DelegateDemoPerson {
    String name
}

class Thing {
    String name
}

def p = new DelegateDemoPerson(name: 'Norman')
def t = new Thing(name: 'Teapot')

def upperCasedName = { delegate.name.toUpperCase() }

upperCasedName.delegate = p
assert upperCasedName() == 'NORMAN'
upperCasedName.delegate = t
assert upperCasedName() == 'TEAPOT'

def target = p
def upperCasedNameUsingVar = { target.name.toUpperCase() }
assert upperCasedNameUsingVar() == 'NORMAN'

// Delegation strategy

class A {
    String name
}

def a = new A(name: 'Igor')
def cl = { name.toUpperCase() }
cl.delegate = a
assert cl() == 'IGOR'

