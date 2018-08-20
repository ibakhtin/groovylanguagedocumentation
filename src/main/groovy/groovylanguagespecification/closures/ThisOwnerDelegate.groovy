package groovylanguagespecification.closures

// Groovy Closures: this, owner, delegate Let's Make a DSL
// https://dzone.com/articles/groovy-closures-owner-delegate

class ClassDemoOne {
    def outerClosure = {
        println this.class.name
        println owner.class.name
        println delegate.class.name
        def nestedClosure = {
            println this.class.name
            println owner.class.name
            println delegate.class.name
        }
        nestedClosure()
    }
}

def closure = ClassDemoOne.newInstance().outerClosure
closure()

// Demo Two

class ClassDemoTwo {
    String stringOne = 'stringOne'
    def outerClosure = {
        println stringOne
        def nestedClosure = {
            println stringOne
        }
        nestedClosure()
    }
}

def classDemoTwoInstance = ClassDemoTwo.newInstance()
def outerClosureOfClassDemoTwo = ClassDemoTwo.newInstance().outerClosure
outerClosureOfClassDemoTwo()
println classDemoTwoInstance.stringOne

// Demo Three

class ClassDemoThree {
    def outerClosure = {
        def stringThree = 'stringThree'
        def nestedClosure = {
            println stringThree
        }
        nestedClosure()
    }
}

ClassDemoThree classDemoThreeInstance = ClassDemoThree.newInstance()
def closureThree = classDemoThreeInstance.outerClosure
closureThree()

// Demo Four

class OtherClassFour {
    String stringFour = "I am over in here in 'OtherClassFour'"
}

class ClassFour {
    def closureFour = {
        println stringFour
    }
}

def closureFour = ClassFour.newInstance().closureFour
closureFour.delegate = OtherClassFour.newInstance()
closureFour()

// Demo Five

class OtherClassFive01 {
    String stringFive = "I am over in here in 'OtherClassFive01'"
}

class OtherClassFive02 {
    String stringFive = "I am over in here in 'OtherClassFive02'"
}

class ClassFive {
    def closureFive = {
        println stringFive
    }
}

def closureFive = ClassFive.newInstance().closureFive

closureFive.delegate = OtherClassFive01.newInstance()
closureFive()

closureFive.delegate = OtherClassFive02.newInstance()
closureFive()

