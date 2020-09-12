package com.caredirection.cadi.product.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.fragment_product_list_detail_simplicity.*

class FragmentDetailSimplicity: Fragment(R.layout.fragment_product_list_detail_simplicity) {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        img_product_list_detail_simplicity_filter.setOnClickListener{
            val intent = Intent(requireContext(), ProductFilterActivity()::class.java)
            startActivity(intent)
        }


    }

    fun ingredientSetting(){

    }
}
data class IngredientDataSimplicity(
    val ingredientTitle: String,
    val standard1: String,
    val standard2: String,
    val standard3: String
)