package edu.programmingclasses2024winter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d("Lifecycle", "OnCreate starting")
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d("Lifecycle", "OnCreate returning")
  }

  override fun onStart() {
    super.onStart()
    Log.d("Lifecycle", "OnStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d("Lifecycle", "OnResume")
  }

  override fun onPause() {
    super.onPause()
    Log.d("Lifecycle", "OnPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d("Lifecycle", "OnStop")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("Lifecycle", "OnDestroy")
  }

  companion object {
    private val HELLO_TEXT_KEY = "HELLO_TEXT"
    val TEXT_INPUT_KEY = "TEXT_INPUT"
  }
}
