package dietplan.app.oqvvwe.ui.detail

import androidx.recyclerview.widget.DiffUtil
import com.example.edamantestapp.data.remoteData.entities.IngredientItem

class DetailDiffCallBack():DiffUtil.ItemCallback<IngredientItem>(){
    override fun areItemsTheSame(oldItem: IngredientItem, newItem: IngredientItem): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: IngredientItem, newItem: IngredientItem): Boolean {
        return oldItem == newItem
    }
}
