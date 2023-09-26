package dietplan.app.oqvvwe.ui.plan

import androidx.recyclerview.widget.DiffUtil
import dietplan.app.oqvvwe.data.db.RecipeDB

class PlanDiffCallBack:DiffUtil.ItemCallback<RecipeDB>() {

    override fun areItemsTheSame(oldItem: RecipeDB, newItem: RecipeDB): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: RecipeDB, newItem: RecipeDB): Boolean {
        return oldItem == newItem
    }
}