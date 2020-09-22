package com.caredirection.cadi.register.user.ingredient

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.register.user.RegisterProductActivity
import com.caredirection.cadi.register.user.ingredient.material.RegisterMaterialFragment
import com.caredirection.cadi.register.user.ingredient.vitamin.RegisterVitaminFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.dialog_register_product_ingredient.view.*

class RegisterIngredientFragment : BottomSheetDialogFragment(){

    private lateinit var rootView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)

        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)!!

            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheet).skipCollapsed = true
            BottomSheetBehavior.from(bottomSheet).isHideable = true
        }

        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.dialog_register_product_ingredient, container, false)

        getIngredientFragment()
        getIngredientMenuTab()

        rootView.btn_register_product_ingredient_apply.setOnClickListener {
            (activity as RegisterProductActivity).getIngredientList()
            dismiss()
        }

        rootView.btn_register_product_ingredient_close.setOnClickListener {
            dismiss()
        }

        return rootView
    }

    private fun getIngredientMenuTab(){
        val menu = arrayListOf("비타민 & 미네랄", "기능성 원료")
        for(i in 0..menu.size){
            rootView.tl_register_product_ingredient.getTabAt(i)?.text = menu[i]
        }

        //setTabSelectedListener()
    }

    private fun getIngredientFragment(){
        val fragments = arrayListOf<Fragment>(RegisterVitaminFragment(), RegisterMaterialFragment())

        val registerIngredientMenuAdapter = RegisterIngredientMenuAdapter(childFragmentManager)
        registerIngredientMenuAdapter.data = fragments

        rootView.vp_register_product_ingredient.adapter = registerIngredientMenuAdapter
        rootView.tl_register_product_ingredient.setupWithViewPager(rootView.vp_register_product_ingredient)

    }

    private fun setTabSelectedListener(){
        rootView.tl_register_product_ingredient.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //rootView.tl_register_product_ingredient.
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })
    }
}