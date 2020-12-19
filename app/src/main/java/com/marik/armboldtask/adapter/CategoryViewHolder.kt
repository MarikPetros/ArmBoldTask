package com.marik.armboldtask.adapter

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.marik.armboldtask.R
import com.marik.armboldtask.model.Category

class
CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var shadowCategory =
        itemView.findViewById<cn.enjoytoday.shadow.ShadowLayout>(R.id.shadow_category)
    private var categoryCard: LinearLayoutCompat = itemView.findViewById(R.id.layout_card_category)
    private var name: TextView = itemView.findViewById(R.id.txt_category_title)
    private var count: TextView = itemView.findViewById(R.id.txt_prod_count)
    var image: AppCompatImageView = itemView.findViewById(R.id.img_category)

    fun bindView(item: Category) {
        shadowCategory.shadowConfig.setShadowColor(
            ResourcesCompat.getColor(
                itemView.context.resources,
                item.shapeShadowColor,
                null
            )
        )
        val categoryBack: Drawable =
            ResourcesCompat.getDrawable(itemView.context.resources, item.backgroundShape, null)!!
        categoryCard.background = categoryBack
        name.text = item.name
        // count must be item.productLis.size concatenated in text, but now hardcoded
        count.text = "75 products"
        image.background =
            ResourcesCompat.getDrawable(itemView.resources, item.categoryImage, null)!!
    }

}
