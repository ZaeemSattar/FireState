package com.zaeem.firestatus


import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory


/**
 * Send a ping to googles primary DNS.
 * If successful, that means we have internet.
 */
class PingNetwork : IPingNetwork {

    // Make sure to execute this on a background thread.
    override fun ping(socketFactory: SocketFactory): Boolean {
        return try {
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}