package edu.programmingclasses2024winter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val HAS_USER_SEEN_WELCOME_SCREEN = booleanPreferencesKey("hasUserSeenWelcomeScreen")
val Context.myDataStore by preferencesDataStore("filename")

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d("Lifecycle", "OnCreate starting")
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d("Lifecycle", "OnCreate returning")
  }

  override fun onStart() {
    super.onStart()

    lifecycleScope.launch {
      val hasUserSeenWelcomeScreen = baseContext
        .myDataStore
        .data
        .map { preferences -> preferences[HAS_USER_SEEN_WELCOME_SCREEN] }
        .first() ?: false

      if (hasUserSeenWelcomeScreen.not()) {
        with(findNavController(R.id.navFragment)) {
          navigate(R.id.actionNavigateToWelcomeDialog)
        }
      }
    }
  }

  companion object {
    private val HELLO_TEXT_KEY = "HELLO_TEXT"
    val TEXT_INPUT_KEY = "TEXT_INPUT"
  }
}
