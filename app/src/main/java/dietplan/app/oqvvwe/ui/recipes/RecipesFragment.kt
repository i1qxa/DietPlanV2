package dietplan.app.oqvvwe.ui.recipes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dietplan.app.oqvvwe.R
import dietplan.app.oqvvwe.databinding.FragmentRecipesBinding
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(RecipesViewModel::class.java) }

    private val rvAdapter by lazy { RecipesRVAdapter() }

    private val queryLD = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupRVAdapter()
        setupRecyclerView()
        setupTextChangeListener()
        lifecycleScope.launch {
            observeTextChange()
        }
    }

    private fun setupTextChangeListener() {
        binding.etQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                queryLD.value = binding.etQuery.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private suspend fun observeTextChange() {
        queryLD.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getRecipeList(it)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.listRecipes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.pbRecipes.visibility = View.GONE
                binding.rvRecipesList.visibility = View.VISIBLE
                binding.tvHitTV.visibility = View.GONE
            }
            rvAdapter.submitList(it)
        }
        viewModel.errorRequest.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvHitTV.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRVAdapter() {
        with(rvAdapter) {
            onItemClickListener = {
                findNavController().navigate(
                    RecipesFragmentDirections.actionRecipesFragmentToDetailFragment(
                        it
                    )
                )
            }
            onBtnAddClickListener = {
                lifecycleScope.launch {
                    viewModel.addRecipe(it)
                    Toast.makeText(
                        requireContext(),
                        "${it.label} ${requireContext().getString(R.string.toast_add_recipe)}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvRecipesList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}