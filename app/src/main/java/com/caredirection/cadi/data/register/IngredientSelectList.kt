package com.caredirection.cadi.data.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.caredirection.cadi.R
import com.caredirection.cadi.register.user.RegisterUnitPicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.super_rabbit.wheel_picker.WheelPicker
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class IngredientSelectList {

    companion object{
        var selectedVitaminList = mutableListOf<String>()
        var selectedMaterialList = mutableListOf<String>()

        fun showUnitPicker(context: Context){
            val contentDialog = BottomSheetDialog(context)
            val contentLayout : LayoutInflater = LayoutInflater.from(context)
            val contentView : View = contentLayout.inflate(R.layout.dialog_register_ingredient, null)

            val npContent : WheelPicker = contentView.findViewById(R.id.np_ingredient_content)
            val btnCancel : TextView = contentView.findViewById(R.id.btn_ingredient_cancel)
            val btnConfirm : TextView = contentView.findViewById(R.id.btn_ingredient_confirm)

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy")
            val formatted = current.format(formatter)

            npContent.setAdapter(RegisterUnitPicker())

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
