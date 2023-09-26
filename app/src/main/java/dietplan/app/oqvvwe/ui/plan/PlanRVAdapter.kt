package dietplan.app.oqvvwe.ui.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import dietplan.app.oqvvwe.R
import dietplan.app.oqvvwe.data.db.RecipeDB

class PlanRVAdapter: ListAdapter<RecipeDB, PlanViewHolder>(PlanDiffCallBack()) {

    var onDeleteClickListener: ((RecipeDB) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.recipe_item_db
        return PlanViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            ivLogo.load(item.imgSmall){
                transformations(RoundedCornersTransformation(20.0f))
            }
            tvName.text = item.label
            tvKcal.text = (item.calories?:0).toString()
            btnDelete.setOnClickListener {
                onDeleteClickListener?.invoke(item)
            }
        }
    }

}