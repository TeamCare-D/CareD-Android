package com.caredirection.cadi.product

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.product.list.adapter.MarketingData
import com.caredirection.cadi.product.list.adapter.ProductMagazineData
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import com.caredirection.cadi.product.search.ProductSearch
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment(R.layout.fragment_product) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val productMagazineRvAdapter =
            ProductMagazineRvAdapter()

        val tag = listOf<String>("방향성", "방향성")

        productMagazineRvAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag)
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )

        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )
        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )
        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )

        rv_product_magazine_interested.adapter = productMagazineRvAdapter

        btn_product_search.setOnClickListener {
            val intent = Intent(requireContext(), ProductSearch::class.java)
            startActivity(intent)
        }

    }


}