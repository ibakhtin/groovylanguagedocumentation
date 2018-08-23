package userguides.groovydevelopmentkit

import groovy.io.FileType
import groovy.io.FileVisitResult

// Reading files

File file = new File('haiku.txt')

file.eachLine { line ->
    println line
}

file.eachLine { line, nb ->
    println "Line $nb: $line"
}

/*
def count = 0, MAXSIZE = 3
new File("haiku.txt").withReader { reader ->
    while (reader.readLine()) {
        if (++count > MAXSIZE) {
            throw new RuntimeException('Haiku should only have 3 verses')
        }
    }
}
*/

//

def list = file.collect { it }
// assert list == ['Into the ancient pond', 'A frog jumps', 'Water\'s sound!']

def array = file as String[]
// assert array == ['Into the ancient pond', 'A frog jumps', 'Water\'s sound!']

byte[] contents = file.bytes
// println contents
// println new String(contents)

// Writing files

file.withWriter('utf-8') { writer ->
    writer.writeLine 'Into the ancient pond'
    writer.writeLine 'A frog jumps'
    writer.writeLine 'Water’s sound!'
}

file.write('')

//

file << '''Into the ancient pond
A frog jumps
Water’s sound!'''

//

println '\n// Traversing file trees\n'

def dir = new File('.')

dir.eachFile { f ->
    println f.name
}

dir.eachFileMatch(~/.*\.txt/) { f ->
    println f.name
}

//

dir = new File('..')

dir.eachFileRecurse { f ->
    println f.name
}

dir.eachFileRecurse(FileType.FILES) { f ->
    println f.name
}

dir = new File('..')

dir.traverse { f ->
    if (f.directory && f.name=='bin') {
        FileVisitResult.TERMINATE
    } else {
        println f.name
        FileVisitResult.CONTINUE
    }
}

// Data and objects

Boolean aBoolean = true
String aString = 'Hello from Groovy'

File dataFile = new File('data.dat')

dataFile.withDataOutputStream { out ->
    out.writeBoolean(aBoolean)
    out.writeUTF(aString)
}

dataFile.withDataInputStream { input ->
    assert input.readBoolean() == aBoolean
    assert input.readUTF() == aString
}

dataFile.delete()
