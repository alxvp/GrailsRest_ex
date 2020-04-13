package org.dbWork

class FileMonitorService {

    def grailsApplication
    HashCountService hashCountService
    def currentHash = ''

    def checkFile() {
        File dumpFile = new File(grailsApplication.config.dumpFile)

        List<String> lineValues = []
        List <String> lines = []

        if(currentHash != hashCountService.getMd5Hash(dumpFile)){

            lines = dumpFile.readLines()

            if (lines.size() > 0){
                if (lines[0].split(';')[0] == 'code') lines.remove(0)
            }

            if (lines.size() >= 1){
                lines.each{
                    lineValues = it.split(';')
                    if(lineValues.size() == 2){
                        println "save data: code - ${lineValues[0]} -- name ${lineValues[1]}"
                        new Country(code: "${lineValues[0]}",
                                    name: "${lineValues[1].replace('"','')}").save()
                    }

                }
            }
            currentHash = hashCountService.getMd5Hash(dumpFile)
        } else {
            println "No cahnges"
        }
    }
}
