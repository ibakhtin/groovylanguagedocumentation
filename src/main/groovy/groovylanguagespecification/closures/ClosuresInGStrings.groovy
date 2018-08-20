package groovylanguagespecification.closures

def x = 1
def gs = "x = ${x}"
assert gs == 'x = 1'

x = 2
assert gs != 'x = 2'

def x2 = 1
def gs2 = "x2 = ${-> x2}"
assert gs2 == 'x2 = 1'

x2 = 2
assert gs2 == 'x2 = 2'

