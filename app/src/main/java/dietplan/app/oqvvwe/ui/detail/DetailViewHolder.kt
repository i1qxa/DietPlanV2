package dietplan.app.oqvvwe.ui.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dietplan.app.oqvvwe.R

class DetailViewHolder(itemView: View):ViewHolder(itemView) {

    val logo = itemView.findViewById<ImageView>(R.id.ivIngredientLogo)
    val name = itemView.findViewById<TextView>(R.id.tvIngredient)

}