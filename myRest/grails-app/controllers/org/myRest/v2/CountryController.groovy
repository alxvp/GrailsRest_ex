package org.myRest.v2

import org.myRest.Country
import org.myRest.PublishService
import org.myRest.CountryService

class CountryController {
	static responseFormats = ['json']
    static namespace = 'v2'

    CountryService countryService
    PublishService publishService

    List<Country> result = []
    Map status =[:]

    def filter() {
        result = []
        if (params?.name) {
            result = countryService.findByName(params.name)
        } else if (params?.code) {
            result = countryService.findByCode(((params.code).toUpperCase()))
        }
        status = checkResult(result, status, namespace)
        respond result, model: status, view: 'getBy'
    }

    def filterLikeName() {
        result = []
        if (params?.name) {
            result= countryService.findByNameIlike("%$params.name%")
        }
        status = checkResult(result, status, namespace)
        respond result, model: status, view: 'getBy'
    }

    def getBy(){
        result = []
        if (params.id){
            if ((params.id).isLong()){
                result = countryService.findById(params.id)
            } else if ((params.id).size() == 2) {
                result = countryService.findByCode((params.id).toUpperCase())
            } else {
                result =  countryService.findByNameIlike(params.id)
            }
        } else {
            result =  Country.list(params)
        }
        status = checkResult(result, status, namespace)
        respond (result, model: status)
    }

    def fileUpload(){
        def file = request.getFile('file')
        if (file.empty) {
            respond([], model:[status: "204",
                               description: ("No file").toString(),
                               version: namespace])
        } else {

            if (publishService.JMSpublish("paramVal1", "paramVal2", file.getBytes())) {
                respond([], model: [status: "200",
                                    description: ("File - ${file.getOriginalFilename()} - successfully uploaded").toString(),
                                    version: namespace])
            } else {
                respond([], model:[status: "500",
                                   description: ("Error upload file - ${file.getOriginalFilename()}").toString(),
                                   version: namespace])
            }
        }
    }

    static private Map checkResult(List<Country> result, Map status, String version) {
        status = [status: "200", description: "Success execution"]
        if (result.size() == 0) {
            status.status = '404'
            status.description = 'Not found'
        }
        status.version = version

        return status
    }
}
