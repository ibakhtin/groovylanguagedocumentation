package groovylanguagespecification.closures

import groovy.time.TimeCategory
import groovy.time.TimeDuration

// CURRYING

// Left curring

def nCopies = { int n, String str -> str * n }
def twice = nCopies.curry(2)

assert twice('bla') == 'blabla'
assert twice('bla') == nCopies(2, 'bla')

// Right currying

def blah = nCopies.rcurry('bla')

assert blah(2) == 'blabla'
assert blah(2) == nCopies(2, 'bla')

// Index based currying

def volume = { Double l, Double w, Double h -> l * w * h }
def fixedWidthVolume = volume.ncurry(1, 2d)
assert volume(3d, 2d, 4d) == fixedWidthVolume(3d, 4d)
def fixedWidthAndHeightVolume = volume.ncurry(1, 2d, 4d)
assert volume(3d, 2d, 4d) == fixedWidthAndHeightVolume(3d)

// Memoization

def fib
fib = { long n -> n < 2 ? n : fib(n - 1) + fib(n - 2) }

Date startTime = Date.newInstance()
assert fib(30) == 832_040
Date stopTime = Date.newInstance()

Long duration = stopTime.time - startTime.time
println duration

//

Closure fibMem
fibMem = { long n -> n < 2 ? n : fibMem(n - 1) + fibMem(n - 2) }.memoize()

startTime = Date.newInstance()
assert fibMem(30) == 832_040
stopTime = Date.newInstance()

duration = stopTime.time - startTime.time
println duration

// Composition

def plus2 = { it + 2 }
def times3 = { it * 3 }

def times3plus2 = plus2 << times3
assert times3plus2(3) == 11
assert times3plus2(2) == plus2(times3(2))

def plus2times3 = times3 << plus2
assert plus2times3(3) == 15
assert plus2times3(5) == times3(plus2(5))

// reverse composition
assert times3plus2(2) == (times3 >> plus2)(2)

// Trampoline

Closure factorial
factorial = { int n, def acc = 1G ->
    if (n < 2) return acc
    factorial.trampoline(n - 1, n * acc)
}
factorial = factorial.trampoline()

assert factorial(1) == 1
assert factorial(5) == 1 * 2 * 3 * 4 * 5

