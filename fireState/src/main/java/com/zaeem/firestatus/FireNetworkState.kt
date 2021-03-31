package com.zaeem.firestatus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FireNetworkState(context: Context) : LiveData<Boolean>() {


    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()
    private val pingNetwork: IPingNetwork by lazy { PingNetwork() }

    private fun checkValidNetworks() {
        postValue(validNetworks.isNotEmpty())
    }

    @SuppressLint("NewApi")
    override fun onActive() {
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)
    }

    @SuppressLint("NewApi")
    override fun onInactive() {
        cm.unregisterNetworkCallback(networkCallback)
    }

    @SuppressLint("NewApi")
    private fun createNetworkCallback() =
        object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                val networkCapabilities = cm.getNetworkCapabilities(network)
                val hasInternetCapability =
                    networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
                if (hasInternetCapability == true) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val hasInternet = pingNetwork.ping(network.socketFactory)
                        if (hasInternet) {
                            withContext(Dispatchers.Main) {
                                validNetworks.add(network)
                                checkValidNetworks()
                            }
                        }
                    }
                }
            }

            override fun onLost(network: Network) {
                validNetworks.remove(network)
                checkValidNetworks()
            }

        }

}