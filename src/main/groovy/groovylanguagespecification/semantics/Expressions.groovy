package groovylanguagespecification.semantics

import java.lang.reflect.Method

void aMethodFoo() { println "This is aMethodFoo." }

assert ['aMethodFoo'] == this.class.methods.name.grep(~/.*Foo/)

void aMethodBar() { println "This is aMethodBar." }
void anotherFooMethod() { println "This is anotherFooMethod." }
void aSecondMethodBar() { println "This is aSecondMethodBar." }

assert ['aMethodBar', 'aSecondMethodBar'] as Set == this.class.methods.name.grep(~/.*Bar/) as Set

assert 'aSecondMethodBar' == this.class.methods.name.grep(~/.*Bar/).sort()[1]

