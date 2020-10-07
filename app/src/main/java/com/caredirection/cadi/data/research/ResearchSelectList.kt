package com.caredirection.cadi.data.research

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.caredirection.cadi.R

class ResearchSelectList {

    companion object{
        var age = 0
        var gender = 0
        var selectedDiseaseList = mutableListOf<Int>()
        var selectedMedicineList = mutableListOf<Int>()
        var selectedAllergyList = mutableListOf<Int>()
        var selectedInterestList = mutableListOf<Int>()

        var checkFirst = true
        var researchActivityList = mutableListOf<Activity>()

        fun showStopDialog(context: Context){
            val deleteDialog = AppCompatDialog(context)
            val deleteLayout : LayoutInflater = LayoutInflater.from(context)
            val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup,null)

            val btnCancel : Button = deleteView.findViewById(R.id.btn_popup_cancel)
            val btnConfirm : Button = deleteView.findViewById(R.id.btn_popup_confirm)
            val txtTitle : TextView = deleteView.findViewById(R.id.txt_popup_tilte)

            if(checkFirst){
                txtTitle.text = "지금 설문을 중단하시면\n케어디의 서비스를 이용할 수 없습니다."
            }
            else{
                txtTitle.text = "설문이 완료되지 않았습니다.\n페이지를 나가시겠습니까?"
            }

            btnCancel.setOnClickListener {
                deleteDialog.cancel()
            }

            btnConfirm.setOnClickListener {
                var num = 0
                if(checkFirst){
                    num = 1
                    resetList()
                }
                (num until researchActivityList.size).forEach {
                    researchActivityList[it].finish()
                }

                deleteDialog.dismiss()
            }

            deleteDialog.setContentView(deleteView)
            deleteDialog.setCanceledOnTouchOutside(false)
            deleteDialog.create()
            deleteDialog.show()
        }

        private fun resetList(){
            age = 0
            gender = 0
            selectedDiseaseList.clear()
            selectedMedicineList.clear()
            selectedAllergyList.clear()
            selectedInterestList.clear()
        }

    }
}