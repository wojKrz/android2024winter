package edu.programmingclasses2024winter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

  lateinit var text: TextView
  lateinit var button: Button
  var editText: TextInputEditText? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    text = findViewById(R.id.helloText)
    button = findViewById(R.id.button)

    try {
      editText = findViewById(R.id.textInput)
    } catch (npe: NullPointerException) { }

    button.setOnClickListener {
      val intent = Intent(this, SecondActivity::class.java)
      intent.putExtra("inputText", editText?.text?.toString() ?: "No text was available")
      startActivity(intent)
    }

    text.text = savedInstanceState?.getString(TEXT_KEY).orEmpty()

    Log.d("Lifecycle", "On create")
  }

  override fun onStart() {
    super.onStart()
    Log.d("Lifecycle", "On start")
  }

  override fun onResume() {
    super.onResume()
    Log.d("Lifecycle", "On resume")
  }

  override fun onPause() {
    super.onPause()
    Log.d("Lifecycle", "On pause")
  }

  override fun onStop() {
    super.onStop()
    Log.d("Lifecycle", "On stop")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("Lifecycle", "On destroy")
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString(TEXT_KEY, text.text.toString())
  }

  companion object {
    private const val TEXT_KEY = "text"
  }
}
