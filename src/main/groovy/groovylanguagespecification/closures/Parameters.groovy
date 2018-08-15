package groovylanguagespecification.closures

// Normal parameters

def closureWithOneArg = { str -> str.toUpperCase() }
assert closureWithOneArg('groovy') == 'GROOVY'

def closureWithOneArgAndExplicitType = { String str -> str.toUpperCase() }
assert closureWithOneArgAndExplicitType('groovy') == 'GROOVY'

def closureWithTwoArgs = { a, b -> a + b }
assert closureWithTwoArgs(1, 2) == 3
assert closureWithTwoArgs('a', 'b') == 'ab' // My addition

def closureWithTwoArgsAndExplicitTypes = { int a, int b -> a+b }
assert closureWithTwoArgsAndExplicitTypes(1,2) == 3

def closureWithTwoArgsAndOptionalTypes = { a, int b -> a + b }
assert closureWithTwoArgsAndOptionalTypes(1,2) == 3
assert closureWithTwoArgsAndOptionalTypes('a', 3) == 'a3' // My addition

def closureWithTwoArgsAndDefaultValue = { int a, int b = 2 -> a + b }
assert closureWithTwoArgsAndDefaultValue(1) == 3

// Implicit parameter

def greeting = { "Hello, $it!" }
assert greeting('Patrick') == 'Hello, Patrick!'

greeting = { it -> "Hello, $it!" }
assert greeting('Patrick') == 'Hello, Patrick!'

// Varargs

def concat1 = { String... args -> args.join('') }
assert concat1('abc','def') == 'abcdef'

def concat2 = { String[] args -> args.join('') }
assert concat2('abc', 'def') == 'abcdef'

def multiConcat = { int n, String... args ->
    args.join('') * n
}
assert multiConcat(2, 'abc', 'def') == 'abcdefabcdef'