package edu.programmingclasses2024winter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

  private lateinit var textView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    textView = findViewById(R.id.secondTextView)

    textView.text = intent.extras?.getString(MainActivity.TEXT_INPUT_KEY)
  }
}
