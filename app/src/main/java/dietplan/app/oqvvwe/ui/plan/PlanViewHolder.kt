package dietplan.app.oqvvwe.ui.plan

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dietplan.app.oqvvwe.R

class PlanViewHolder(itemView:View):ViewHolder(itemView) {
    val ivLogo = itemView.findViewById<ImageView>(R.id.ivRecipeLogo)
    val tvName = itemView.findViewById<TextView>(R.id.tvRecipeName)
    val tvKcal = itemView.findViewById<TextView>(R.id.tvKcalValue)
    val btnDelete = itemView.findViewById<ImageView>(R.id.btnDelete)
}