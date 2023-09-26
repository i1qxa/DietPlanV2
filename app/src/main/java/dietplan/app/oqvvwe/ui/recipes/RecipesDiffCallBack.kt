package dietplan.app.oqvvwe.ui.recipes

import androidx.recyclerview.widget.DiffUtil
import com.example.edamantestapp.data.remoteData.entities.RecipeItem
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort

class RecipesDiffCallBack:DiffUtil.ItemCallback<RecipeItemShort>() {

    override fun areItemsTheSame(oldItem: RecipeItemShort, newItem: RecipeItemShort): Boolean {
        return oldItem.label == newItem.label
    }

    override fun areContentsTheSame(oldItem: RecipeItemShort, newItem: RecipeItemShort): Boolean {
        return oldItem.label == newItem.label
    }
}