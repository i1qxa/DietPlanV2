package dietplan.app.oqvvwe.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import dietplan.app.oqvvwe.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val rvAdapter by lazy { DetailRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeItem = args.recipeItemShort
        setupRecyclerView()
        binding.ivLogo.load(recipeItem.imgRegular){
            transformations(RoundedCornersTransformation(20.0f))
        }
        binding.tvRecipeName.text = recipeItem.label
        binding.tvKcalValue.text = (recipeItem.calories?:1).toString()
        binding.tvTotalTimeValue.text = (recipeItem.totalTime?:10).toString()
        rvAdapter.submitList(recipeItem.ingredientsList)
    }

    private fun setupRecyclerView() {
        with(binding.rvIngredientsList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                androidx.recyclerview.widget.RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}