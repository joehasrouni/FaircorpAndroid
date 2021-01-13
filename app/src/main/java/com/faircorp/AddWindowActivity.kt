package com.faircorp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.Status
import com.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddWindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    //Adding a window
    fun saveWindow(view: View) {

        //getting information from the activity
        var name = findViewById<EditText>(R.id.window_name).text.toString()
        var roomId = findViewById<EditText>(R.id.room_id).text.toString().toLong()
        var status: Status = Status.OPEN
        if (findViewById<RadioButton>(R.id.radioButtonOpen).isSelected) {
            status = Status.OPEN
        }
        if (findViewById<RadioButton>(R.id.radioButtonClose).isSelected) {
            status = Status.CLOSED
        }


        val newWindow = WindowDto(null, name, roomId, status)

        //posting new window to the api
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching {
                ApiServices().windowsApiService.createWindow(newWindow).execute();
            }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(this@AddWindowActivity, "Added", Toast.LENGTH_SHORT).show()
                    }
                }

                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error while adding",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }


        }
    }
}