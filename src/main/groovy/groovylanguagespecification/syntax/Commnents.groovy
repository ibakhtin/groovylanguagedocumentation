package groovylanguagespecification.syntax

// Single line comments

// a standalone single line comment
println "hello" // a comment till the end of the line

// Multiline comments

/* a standalone multiline comment
   spanning two lines */
println "hello" /* a multiline comment starting
                   at the end of a statement */
println 1 /* one */ + 2 /* two */

// GroovyDoc comment

/**
 * A Class description
 */
class Person {
    /** the name of the person */
    String name

    /**
     * Creates a greeting method for a certain person.
     *
     * @param otherPerson the person to greet
     * @return a greeting message
     */
    String greet(String otherPerson) {
        "Hello ${otherPerson}"
    }
}

// Shebang line

//#!/usr/bin/env groovy
//println "Hello from the shebang line"
