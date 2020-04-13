package org.dbWork

class CronReadFileJob {
    FileMonitorService fileMonitorService
    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }
    def count = 1
    def execute() {
        // execute job
        fileMonitorService.checkFile()
    }
}
