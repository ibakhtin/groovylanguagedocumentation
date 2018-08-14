package groovylanguagespecification.operators

import java.util.regex.Matcher
import java.util.regex.Pattern

// Pattern operator

def p = ~/foo/
assert p instanceof Pattern

//

def pattern = 'bar'

p = ~'foo'
p = ~"foo"
p = ~$/dollar/slashy $ string/$
p = ~"${pattern}"

// Find operator

def text = "some text to match"
def m = text =~ /match/
assert m instanceof Matcher
if (!m) {
    throw new RuntimeException("Oops, text not found!")
}

// Match operator

m = text ==~ /match/
assert m instanceof Boolean
if (m) {
    throw new RuntimeException("Should not reach that point!")
}