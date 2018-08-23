package groovylanguagespecification.semantics

// Optional parentheses

println 'Hello World'
def maximum = Math.max 5, 10

println()
println(Math.max(5, 10))

// Optional semicolons

assert true;

assert true

boolean a = true; assert a

// Optional return keyword

int add(int a, int b) {
    a + b
}

assert add(1, 2) == 3

// Optional public keyword

public class Server01 {
    public String toString() { "a server" }
}

class Server02 {
    String toString() { "a server" }
}