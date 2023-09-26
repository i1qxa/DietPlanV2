package dietplan.app.oqvvwe.ui.recipes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dietplan.app.oqvvwe.R

class RecipesViewHolder(itemView:View):ViewHolder(itemView) {
    val ivLogo = itemView.findViewById<ImageView>(R.id.ivRecipeLogo)
    val tvName = itemView.findViewById<TextView>(R.id.tvRecipeName)
    val tvKcal = itemView.findViewById<TextView>(R.id.tvKcalValue)
    val btnAdd = itemView.findViewById<ImageView>(R.id.btnAddRecipe)
}