package com.caredirection.cadi.product

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ProductMagazineData
import com.caredirection.cadi.adapter.ProductMagazineRvAdapter
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment(R.layout.fragment_product) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ProductMagazineRvAdapter = ProductMagazineRvAdapter(requireContext())


        val tag = listOf<String>("방향성","방향성")

        ProductMagazineRvAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
        ProductMagazineRvAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
        ProductMagazineRvAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
        ProductMagazineRvAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))

        rv_product_magazine_interested.layoutManager = LinearLayoutManager(requireContext())
        rv_product_magazine_interested.adapter = ProductMagazineRvAdapter

        custumRv(ProductMagazineRvAdapter.items.size)
    }

    fun custumRv(size: Int){

        var magazineWidth = View.MeasureSpec.makeMeasureSpec(rv_product_magazine_interested.width, rv_product_magazine_interested.height)
        var magazineHeight = 0

        for(i in 1.. size){
            rv_product_magazine_interested.measure(magazineWidth, View.MeasureSpec.UNSPECIFIED)
            magazineHeight += rv_product_magazine_interested.measuredHeight
        }

        var params = rv_product_magazine_interested.layoutParams
        params.height = magazineHeight
        rv_product_magazine_interested.setLayoutParams(params)
        rv_product_magazine_interested.requestLayout()
    }
}