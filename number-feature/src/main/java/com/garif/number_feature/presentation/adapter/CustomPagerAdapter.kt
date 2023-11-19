package com.garif.number_feature.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.imageview.ShapeableImageView


class CustomPagerAdapter(
    private val mContext: Context,
    private val imageUrls: List<String>,
) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout =
            inflater.inflate(com.garif.core.R.layout.item_photo, container, false) as ViewGroup
        layout.findViewById<ShapeableImageView>(com.garif.core.R.id.iv_photo)
            .load(imageUrls[position]) {
                transformations(
                    RoundedCornersTransformation(
                        mContext.resources.getDimension(com.garif.core.R.dimen.x2)
                    )
                )
            }
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = imageUrls.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}