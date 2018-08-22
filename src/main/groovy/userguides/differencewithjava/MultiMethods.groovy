package userguides.differencewithjava

import groovy.transform.CompileStatic

@CompileStatic
class StaticClass {
    def static method(String arg) { 'String' }
    def static method(Object arg) { 'Object' }
}

class DynamicClass {
    def static method(String arg) { 'String' }
    def static method(Object arg) { 'Object' }
}

Object anObject = 'String'

println StaticClass.method(anObject)
println DynamicClass.method(new Date())