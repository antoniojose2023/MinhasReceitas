package br.com.antoniojoseuchoa.minhasreceitas.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.antoniojoseuchoa.minhasreceitas.databinding.FragmentFirstBinding
import br.com.antoniojoseuchoa.minhasreceitas.presentation.dialog.DialogAddRecipe
import br.com.antoniojoseuchoa.minhasreceitas.viewmodel.RecipeViewModel
import br.com.antoniojoseuchoa.minhasreceitas.viewmodel.States

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val adapterRecipe by lazy { AdapterRecipe() }

    private val viewModelRecipe by viewModels<RecipeViewModel>{
        RecipeViewModel.Factory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       listenersLista()
       setupListenerFloatingButton()

        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipes.adapter = adapterRecipe



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listenersLista(){
        viewModelRecipe.states.observe(viewLifecycleOwner){ state ->
            when(state){
                States.Empty -> showMessage("Não existe ainda receitas")
                States.Loader -> showMessage("Carregando . . .")
                is States.OnErro -> {showMessage(state.erro)}
                is States.OnSucess -> {adapterRecipe.submitList( state.list )}
            }
        }
    }

    private fun showMessage(mensage: String){
          Toast.makeText(requireActivity(), "$mensage", Toast.LENGTH_LONG).show()
    }

    private fun showDialog(){
         DialogAddRecipe.show(title = "Nova receita", placeHodler = "nome da receita", fragmentManager = parentFragmentManager)
    }

    private fun setupListenerFloatingButton(){
        binding.floatAdd.setOnClickListener {
            showDialog()
        }

        setFragmentResultListener(DialogAddRecipe.FRAGMENT_RESULT){ key, bundle ->
            val nome = bundle.getString(DialogAddRecipe.EDIT_TEXT_VALUE) ?: ""
            viewModelRecipe.insert(nome)
        }

    }
}