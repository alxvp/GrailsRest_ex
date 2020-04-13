package org.myRest
import org.myRest.Country
import grails.gorm.services.Service

@Service(Country)
interface CountryService {

    List<Country> findByName(String name)

    List<Country> findByNameIlike(String name)

    List<Country> findByCode(String code)

    List<Country> findById(String id)

}