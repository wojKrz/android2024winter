package edu.programmingclasses2024winter

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
import edu.programmingclasses2024winter.databinding.DialogWelcomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeDialog : DialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View =
    DialogWelcomeBinding.inflate(inflater, container, false)
      .also {
        it.welcomeDialogButton.setOnClickListener {
          onOkClicked()
        }
      }.root


  override fun onDismiss(dialog: DialogInterface) {
    super.onDismiss(dialog)

    GlobalScope.launch {
      context?.myDataStore?.edit { preferences ->
        Log.d("Dismissing", "Editing preferences")
        preferences[HAS_USER_SEEN_WELCOME_SCREEN] = true
      }
    }.invokeOnCompletion {
      Log.d("Dismissing", "Completed storing, $it")
    }
  }

  private fun onOkClicked() {
    dismiss()
  }
}
