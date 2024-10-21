package edu.programmingclasses2024winter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)
    val data = intent.getStringExtra("inputText")

    findViewById<TextView>(R.id.dataText).text = data
  }
}
