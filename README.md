	
  [![](https://jitpack.io/v/ZaeemSattar/FireState.svg)](https://jitpack.io/#ZaeemSattar/FireState)
  
 

<h1 align="center">Hello there! My name is Zaeem Sattar 👋🤓</h1>
<h2 align="center">& i am the author of FireState a micro network state observer library 🚀😎</h2>
<h2 align="center">& i bet you this will make your life easier 🥳🤝</h2>


<h1 align="center">Who I AM ? 👀</h1>

<p align="center">I'm a Android Developer & i am working in a multinational software development company in Pakistan as Senior Android Engineer </p>

<h1> Why i build this awesome library ? </h1>

<p> As most of my apps use network access to use any feature like Web service or anything that needs network access. So i thought to build my own library which i can use in al of my products</p>



  
  How to add FireState in your project
  
  
  Step 1 :
  Add it in your root build.gradle at the end of repositories:

 
  allprojects {
  
		repositories {
			...
			maven { url 'https://jitpack.io' }
      }
  }
  
  Step 2 :
  
  	dependencies {
	        implementation 'com.github.ZaeemSattar:FireState:Tag'
	}
  
  
  That's it !
  
  Now time to use it in your live project.
  
      Step 1 : You can create its variable 
      
      private lateinit var fireNetworkState: FireNetworkState
      
      Step 2 :
      
         fireNetworkState = FireNetworkState(this)

        fireNetworkState.observe(this, Observer { hasNetwork ->

            if (hasNetwork)
                Toast.makeText(this, "Network is back!!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Network gone :(", Toast.LENGTH_SHORT).show()
        })
        
        
        
