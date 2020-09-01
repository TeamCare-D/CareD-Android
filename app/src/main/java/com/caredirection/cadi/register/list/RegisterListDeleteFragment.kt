package com.caredirection.cadi.register.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.dialog_popup.view.*

class RegisterListDeleteFragment : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_popup, container, false)

        rootView.btn_popup_cancel.setOnClickListener {
            dismiss()
        }

        rootView.btn_popup_confirm.setOnClickListener {
            dismiss()
        }

        return rootView
    }
}