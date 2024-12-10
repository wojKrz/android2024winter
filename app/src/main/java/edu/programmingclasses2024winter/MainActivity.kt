package edu.programmingclasses2024winter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val HAS_USER_SEEN_WELCOME_SCREEN = booleanPreferencesKey("hasUserSeenWelcomeScreen")
val Context.datastore by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()

    lifecycleScope.launch {
      val hasUserSeenWelcome = datastore.data
        .map { preferences ->
          preferences[HAS_USER_SEEN_WELCOME_SCREEN] ?: false
        }.first()

      if (hasUserSeenWelcome.not()) {
        with(findNavController(R.id.fragment_container)) {
          navigate(R.id.actionNavigateToWelcomeDialog)
        }
      }
    }
  }
}
