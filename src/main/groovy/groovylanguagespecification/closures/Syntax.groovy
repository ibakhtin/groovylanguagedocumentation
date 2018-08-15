package groovylanguagespecification.closures

// Defining a closure

def item = 0

cl1 = { item++ }

cl2 = { -> item++ }

cl3 = { println it }

cl4 = { it -> println it }

cl5 = { name -> println name }

cl6 = { String x, int y ->
    println "hey ${x} the value is ${y}"
}

cl7 = { reader ->
    def line = reader.readLine()
    line.trim()
}

// Closures as an object

def listener = { e -> println "Clicked on $e.source" }
assert listener instanceof Closure
Closure callback = { println 'Done!' }
Closure<Boolean> isTextFile = {
    File it -> it.name.endsWith('.txt')
}

// Calling closure

def code = { 123 }
assert code() == 123
assert code.call() == 123

def isOdd = { int i -> i % 2 != 0 }
assert isOdd(3)
assert !isOdd.call(2)

def isEven = { it % 2 == 0 }
assert !isEven(3)
assert isEven.call(2)
