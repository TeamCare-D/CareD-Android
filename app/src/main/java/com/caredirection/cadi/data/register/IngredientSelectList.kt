package com.caredirection.cadi.data.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.caredirection.cadi.R
import com.caredirection.cadi.register.user.RegisterUnitPicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.super_rabbit.wheel_picker.WheelPicker

class IngredientSelectList {

    companion object{
        var selectedVitaminList = mutableListOf<String>()
        var selectedMaterialList = mutableListOf<String>()

        fun getSelectedIngredientList() : List<RvSelectListItem>{
            val ingredientSelectedList = mutableListOf<RvSelectListItem>()

            (0 until selectedVitaminList.size).forEach {
                val item = RvSelectListItem(
                    name = selectedVitaminList[it],
                    unit = getIngredientUnit(selectedVitaminList[it])
                )

                ingredientSelectedList.add(item)
            }

            (0 until selectedMaterialList.size).forEach {
                val item = RvSelectListItem(
                    name = selectedMaterialList[it],
                    unit = getIngredientUnit(selectedMaterialList[it])
                )

                ingredientSelectedList.add(item)
            }

            return ingredientSelectedList
        }

        fun getIngredientUnit(ingredient : String) : List<String>{
            var group1 = listOf("비타민A","비타민D")
            var group2 = listOf("비타민E")
            var group3 = listOf("비타민B12","비오틴","엽산","셀레늄","비타민K1","비타민K2","구리","크롬","요오드","몰리브덴")
            var group4 = listOf("비타민B1","비타민B2","비타민B3","비타민B5","비타민6","비타민C","칼슘","마그네슘","철분","아연","망간","칼륨","인")
            var group5 = listOf("프로바이오틱스")
            var group6 = listOf("가르시니아","쿄큐텐","오메가3","홍삼","루테인","밀크씨슬")

            when {
                group1.contains(ingredient) -> {
                    return listOf("µg(mcg)","IU","mg")
                }
                group2.contains(ingredient) -> {
                    return listOf("mg","IU","µg(mcg)")
                }
                group3.contains(ingredient) -> {
                    return listOf("µg(mcg)","mg")
                }
                group4.contains(ingredient) -> {
                    return listOf("mg","µg(mcg)")
                }
                group5.contains(ingredient) -> {
                    return listOf("억 마리")
                }
                group6.contains(ingredient) -> {
                    return listOf("mg")
                }
                else -> return listOf("")
            }

        }

        fun showUnitPicker(context: Context, units: List<String>){
            val contentDialog = BottomSheetDialog(context)
            val contentLayout : LayoutInflater = LayoutInflater.from(context)
            val contentView : View = contentLayout.inflate(R.layout.dialog_register_ingredient, null)

            val npContent : WheelPicker = contentView.findViewById(R.id.np_ingredient_unit)
            val btnCancel : TextView = contentView.findViewById(R.id.btn_ingredient_cancel)
            val btnConfirm : TextView = contentView.findViewById(R.id.btn_ingredient_confirm)

            npContent.setAdapter(RegisterUnitPicker(units))

            btnCancel.setOnClickListener {
                contentDialog.cancel()

            }

            btnConfirm.setOnClickListener {
//                btn_year.text = npYear.getCurrentItem()
//                btn_year?.isChecked = true

                contentDialog.dismiss()

            }

            contentDialog.behavior.isHideable = false
            contentDialog.setContentView(contentView)
            contentDialog.setCanceledOnTouchOutside(false)
            contentDialog.create()
            contentDialog.show()
        }
    }
}
