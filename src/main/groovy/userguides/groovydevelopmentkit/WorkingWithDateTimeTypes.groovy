package userguides.groovydevelopmentkit

import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.OffsetTime
import java.time.ZoneOffset

// Formatting and parsing

def date = LocalDate.parse('июн 3, 04', 'MMM d, yy')
assert date == LocalDate.of(2004, Month.JUNE, 3)

def time = LocalTime.parse('4:45', 'H:mm')
assert time == LocalTime.of(4, 45, 0)

def offsetTime = OffsetTime.parse('09:47:51-1234', 'HH:mm:ssZ')
assert offsetTime == OffsetTime.of(9, 47, 51, 0, ZoneOffset.ofHoursMinutes(-12, -34))

def start = System.nanoTime()
def stop = System.nanoTime()

println stop - start