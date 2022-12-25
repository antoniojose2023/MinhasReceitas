package br.com.antoniojoseuchoa.minhasreceitas.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import br.com.antoniojoseuchoa.minhasreceitas.databinding.ItemRecipeBinding
import br.com.antoniojoseuchoa.minhasreceitas.databinding.ItemRecipeDialogBinding

class DialogAddRecipe: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding : ItemRecipeDialogBinding

        val titleText= arguments?.getString(TITLE_TEXT) ?:  throw IllegalArgumentException("Ops texto não encontrado")
        val placeHolder = arguments?.getString(PLACE_HOLDER) ?:  throw IllegalArgumentException("Ops texto não encontrado")


        return activity.let {
            binding = ItemRecipeDialogBinding.inflate(requireActivity().layoutInflater).apply {
                tvLabelRecipe.text = titleText
                editRecipe.hint = placeHolder
            }

            AlertDialog.Builder(it).setView( binding.root ).setPositiveButton("Confirmar"){ _, _ ->
                setFragmentResult(FRAGMENT_RESULT, bundleOf(
                    EDIT_TEXT_VALUE to binding.editRecipe.text.toString()
                )
            )
            }.setNegativeButton("Cancelar"){ _, _ ->
                dismiss()
            }.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

    companion object{
        const val TITLE_TEXT = "text_title"
        const val PLACE_HOLDER = "place_holder"

        const val FRAGMENT_RESULT= "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE= "EDIT_TEXT_VALUE"


        fun show(
            title: String,
            placeHodler: String,
            fragmentManager: FragmentManager,
            tag: String = DialogAddRecipe::class.simpleName.toString()
        ){

            DialogAddRecipe().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                    PLACE_HOLDER to placeHodler
                )
            }.show(
                fragmentManager,
                tag
            )
        }

    }

}