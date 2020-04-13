package myrest

import org.myRest.Country

class BootStrap {

    def init = { servletContext ->
        new Country(name: 'Russia', code: 'RU').save()
        new Country(name: 'USA', code: 'US').save()
        new Country(name: 'Ukraine', code: 'UA').save()
    }
    def destroy = {
    }
}
