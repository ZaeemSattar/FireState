package com.zaeem.firestatus

import javax.net.SocketFactory

interface IPingNetwork {

    fun ping(socketFactory: SocketFactory): Boolean
}