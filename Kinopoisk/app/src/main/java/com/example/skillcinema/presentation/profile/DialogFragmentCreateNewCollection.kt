package com.example.skillcinema.presentation.profile

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.skillcinema.databinding.ItemDialogNewCollectionBinding

class DialogFragmentCreateNewCollection: DialogFragment() {
    private var newName: String? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bindingView = ItemDialogNewCollectionBinding.inflate(layoutInflater)

        val dialogNewCollection = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle("Придумайте название вашей новой коллекции")
            .setView(bindingView.root)
            .setPositiveButton("Готово", null)
            .create()

        dialogNewCollection.setOnShowListener {
            dialogNewCollection.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                newName = bindingView.editCollectionName.text.toString()
                if (newName!!.isBlank()) {
                    return@setOnClickListener
                } else {
                    Toast.makeText(requireContext(), "Создана коллекция $newName", Toast.LENGTH_LONG)
                        .show()
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to newName))
                dismiss()
            }
        }
        return dialogNewCollection
    }


    companion object {
        val TAG = DialogFragmentCreateNewCollection::class.java.simpleName
        val REQUEST_KEY = "$TAG RequestKey"
        val KEY_RESPONSE = "KeyResponse"
    }
}