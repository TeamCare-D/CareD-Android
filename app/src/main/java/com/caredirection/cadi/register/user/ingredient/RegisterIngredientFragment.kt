package com.caredirection.cadi.register.user.ingredient

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.dialog_register_product_ingredient.view.*

class RegisterIngredientFragment : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //getIngredientMenuTab()

        val rootView = inflater.inflate(R.layout.dialog_register_product_ingredient, container, false)

        rootView.btn_register_product_ingredient_apply.setOnClickListener {
            dismiss()
        }

        return rootView
    }

//    private fun getIngredientMenuTab(){
//        val fragments = arrayListOf<Fragment>(RegisterVitaminFragment())
//        val registerIngredientMenuAdapter = RegisterIngredientMenuAdapter(childFragmentManager)
//        registerIngredientMenuAdapter.data = fragments
//        Log.d("명",registerIngredientMenuAdapter.count.toString())
//        vp_register_product_ingredient.adapter = registerIngredientMenuAdapter
//        tl_register_product_ingredient.setupWithViewPager(vp_register_product_ingredient)
//
//        val menu = arrayListOf("Repositories","Follow")
//        for(i in 0..menu.size){
//            tl_register_product_ingredient.getTabAt(i)?.text = menu[i]
//        }
//
//
//    }
}