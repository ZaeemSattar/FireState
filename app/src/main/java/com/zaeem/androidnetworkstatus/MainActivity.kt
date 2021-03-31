package com.zaeem.androidnetworkstatus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zaeem.firestatus.FireNetworkState

class MainActivity : AppCompatActivity() {


    private lateinit var fireNetworkState: FireNetworkState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fireNetworkState = FireNetworkState(this)

        fireNetworkState.observe(this, Observer { hasNetwork ->

            if (hasNetwork)
                Toast.makeText(this, "Network is back!!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Network gone :(", Toast.LENGTH_SHORT).show()
        })
    }
}