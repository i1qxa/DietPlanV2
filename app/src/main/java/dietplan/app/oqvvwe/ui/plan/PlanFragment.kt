package dietplan.app.oqvvwe.ui.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dietplan.app.oqvvwe.databinding.FragmentPlanBinding
import kotlinx.coroutines.launch

class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(PlanViewModel::class.java) }

    private val rvAdapter by lazy { PlanRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupFabClickListener()
        setupRVAdapter()
    }

    private fun setupRecyclerView() {
        with(binding.rvDietPlan) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupRVAdapter() {
        with(rvAdapter) {
            onDeleteClickListener = {
                if (it.uri != null) {
                    lifecycleScope.launch {
                        viewModel.deleteRecipe(it)
                    }
                }
            }
        }
    }

    private fun setupFabClickListener() {
        binding.fabAddRecipes.setOnClickListener {
            findNavController().navigate(PlanFragmentDirections.actionNavigationPlanToRecipesFragment())
        }
    }

    private fun observeViewModel() {
        viewModel.recipeList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
            if (it.isNotEmpty()){
                binding.rvDietPlan.visibility = View.VISIBLE
                binding.tvHintEmptyPlan.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}