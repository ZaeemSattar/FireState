package com.zaeem.firestatus


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import javax.net.SocketFactory

class PingNetwork : IPingNetwork {

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

    override suspend fun ping(): Boolean {
        return withContext(Dispatchers.IO)
        {
            try {
                val ipAddr: InetAddress = InetAddress.getByName("google.com")
                !ipAddr.equals("")
            } catch (e: IOException) {
                false
            }


        }


    }
}