package edu.programmingclasses2024winter.welcome

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import edu.programmingclasses2024winter.HAS_USER_SEEN_WELCOME_SCREEN
import edu.programmingclasses2024winter.databinding.DialogWelcomeBinding
import edu.programmingclasses2024winter.datastore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeDialog : DialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return DialogWelcomeBinding.inflate(inflater, container, false)
      .also { binding ->
        binding.welcomeDialogButton.setOnClickListener {
          onOkButtonClick()
        }
      }.root
  }

  override fun onDismiss(dialog: DialogInterface) {
    GlobalScope.launch {
      context?.datastore?.edit { preferences ->
        Log.d("Dismissing dialog", "Started edition")
        preferences[HAS_USER_SEEN_WELCOME_SCREEN] = true
        Log.d("Dismissing dialog", "Finished assignment")
      }
      Log.d("Dismissing dialog", "Finished edition")
    }.invokeOnCompletion {
      Log.d("Dismissing dialog", "Completed")
    }

    super.onDismiss(dialog)
  }

  private fun onOkButtonClick() {
    dismiss()
  }
}
