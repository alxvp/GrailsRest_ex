package org.dbWork

//import grails.gorm.transactions.Transactional
//
//@Transactional
class HashCountService {

    static getMd5Hash (File file) {
        def digest = java.security.MessageDigest.getInstance('MD5')
        def inputstream = file.newInputStream()
        def buffer = new byte[16384]
        def len

        while((len=inputstream.read(buffer)) > 0) {
            digest.update(buffer, 0, len)
        }
        def sha1sum = digest.digest()

        def result = ""
        for(byte b : sha1sum) {
            result += toHex(b)
        }
        return result
    }

    private static hexChr(int b) {
        return Integer.toHexString(b & 0xF)
    }

    private static toHex(int b) {
        return hexChr((b & 0xF0) >> 4) + hexChr(b & 0x0F)
    }

}
