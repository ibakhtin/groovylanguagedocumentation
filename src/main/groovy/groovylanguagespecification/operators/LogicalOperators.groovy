package groovylanguagespecification.operators

assert !false
assert true && true
assert true || false

// Precedence

assert (!false && false) == false

//

assert true || true && false

// Short-circuiting

boolean checkIfCalled() {
    called = true
}

called = false
true || checkIfCalled()
assert !called

called = false
false || checkIfCalled()
assert called

called = false
false && checkIfCalled()
assert !called

called = false
true && checkIfCalled()
assert called