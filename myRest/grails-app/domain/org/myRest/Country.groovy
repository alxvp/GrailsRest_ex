package org.myRest
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic

class Country {
    Long id
    String name
    String code

    static constraints = {
        id unique: true
        name blank:false
        code blank:false
    }
}

