package groovylanguagespecification.programstructure

// Default imports

import java.lang.*
import java.util.*
import java.io.*
import java.net.*
import groovy.lang.*
import groovy.util.*
import java.math.BigInteger
import java.math.BigDecimal

// Simple import

// importing the class MarkupBuilder
import groovy.xml.MarkupBuilder

// using the imported class to create an object
def xml = new MarkupBuilder()

assert xml != null

// Start import

import groovy.xml.*

def markupBuilder = new MarkupBuilder()

assert markupBuilder != null

assert new StreamingMarkupBuilder() != null

// Static import

import static java.lang.Boolean.FALSE

assert !FALSE //use directly, without Boolean prefix!

//

import static java.lang.String.format

class SomeClass {

    String format(Integer i) {
        i.toString()
    }

    static void main(String[] args) {
        assert format('String') == 'String'
        assert new SomeClass().format(Integer.valueOf(1)) == '1'
    }
}

// Static import aliasing

import static java.util.Calendar.getInstance as now

assert now().class == Calendar.getInstance().class

// Static star import

import static java.lang.Math.*

assert sin(0) == 0.0
assert cos(0) == 1.0

// Import aliasing

import java.util.Date
import java.sql.Date as SQLDate

Date utilDate = new Date(1000L)
SQLDate sqlDate = new SQLDate(1000L)

assert utilDate instanceof java.util.Date
assert sqlDate instanceof java.sql.Date

