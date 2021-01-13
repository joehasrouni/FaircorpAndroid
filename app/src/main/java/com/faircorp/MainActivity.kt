package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"


class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the button */
    fun addWindow(view: View) {

        Toast.makeText(this, "You chose to add a room", Toast.LENGTH_LONG).show()
        // Do something in response to button
        val intent = Intent(this,AddWindowActivity::class.java)

        startActivity(intent)
    }


}
