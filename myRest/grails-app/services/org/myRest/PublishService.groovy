package org.myRest


class PublishService {
    def grailsApplication

    boolean JMSpublish(String param1 = '', String param2 = '', byte[] data){
        try{
            File getFile = new File(grailsApplication.config.dumpFile)
            getFile.delete()
            getFile << data
        } catch (e) {
            return false
        }
        return true
    }
}
