package com.caredirection.cadi.product.detail

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import com.caredirection.cadi.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class bottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var pickerCallback: pickerCallback
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.view_bottom_sheet, container, false)

        val picker_view_bottom_sheet: NumberPicker = view.findViewById(R.id.picker_view_bottom_sheet)

        picker_view_bottom_sheet.minValue = 0
        picker_view_bottom_sheet.maxValue = 2

        picker_view_bottom_sheet.displayedValues = arrayOf("60", "90", "180")


        val txt_view_bottom_sheet_complete: TextView = view.findViewById(R.id.txt_view_bottom_sheet_complete)
        txt_view_bottom_sheet_complete.setOnClickListener{

            when(picker_view_bottom_sheet.value){
                0 -> pickerCallback.callback("60")
                1 -> pickerCallback.callback("90")
                2 -> pickerCallback.callback("180")
            }
            this.dismiss()
        }

        picker_view_bottom_sheet.textColor = Color.parseColor("#358fff")


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is pickerCallback){
            pickerCallback = context
        }
    }
}