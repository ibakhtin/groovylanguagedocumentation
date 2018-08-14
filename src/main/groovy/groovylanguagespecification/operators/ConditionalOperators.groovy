package groovylanguagespecification.operators

// Not operator

assert (!true)    == false
assert (!'foo')   == false
assert (!'')      == true

// Ternary operator

String result
String string = ''

if (string != null && string.length() > 0) {
    result = 'Found'
} else {
    result = 'Not found'
}

assert result == 'Not found'

string = 'some text'

result = (string != null && string.length() > 0) ? 'Found' : 'Not found'
assert result == 'Found'

result = string ? 'Found' : 'Not found'
assert result == 'Found'

// Elvis operator

class User { String name }
def user = new User()

def displayName = user.name ? user.name : 'Anonymous'
assert displayName == 'Anonymous'

displayName = user.name ?: 'Anonymous'
assert displayName == 'Anonymous'

