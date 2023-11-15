package com.garif.hotel_feature.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.garif.hotel_feature.R
import com.google.android.material.imageview.ShapeableImageView


class CustomPagerAdapter(
    private val mContext: Context,
    private val imageUrls: List<String>,
) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.item_photo, container, false) as ViewGroup
        layout.findViewById<ShapeableImageView>(R.id.iv_photo).load(imageUrls[position])
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