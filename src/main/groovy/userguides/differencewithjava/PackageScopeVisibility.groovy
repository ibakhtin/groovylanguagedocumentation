package userguides.differencewithjava

import groovy.transform.PackageScope

class PersonOne {
    String name
    Integer id
}

class PersonTwo {
    @PackageScope String name
    Integer id
}

