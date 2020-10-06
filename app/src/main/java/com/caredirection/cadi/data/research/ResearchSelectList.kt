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
        private var age = 0
        private var gender = 0
        private var selectedDiseaseList = mutableListOf<Int>()
        private var selectedMedicineList = mutableListOf<Int>()
        private var selectedAllergyList = mutableListOf<Int>()
        private var selectedInterestList = mutableListOf<Int>()

        private var checkFirst = true
        var researchActivityList = mutableListOf<Activity>()

        fun setAge(age : String){
            this.age = age.toInt()
        }

        fun getAge() : Int{
            return age
        }

        fun setGender(gender : Int){
            this.gender = gender
        }

        fun getGender() : Int{
            return gender
        }

        fun setDiseaseList(diseaseList: MutableList<Int>){
            selectedDiseaseList = diseaseList
        }

        fun getDiseaseList() : MutableList<Int>{
            return selectedDiseaseList
        }

        fun setMedicineList(medicineList: MutableList<Int>){
            selectedMedicineList = medicineList
        }

        fun getMedicineList() : MutableList<Int>{
            return selectedMedicineList
        }

        fun setAllergyList(allergyList: MutableList<Int>){
            selectedAllergyList = allergyList
        }

        fun getAllergyList() : MutableList<Int>{
            return selectedAllergyList
        }

        fun setInterestList(interestList: MutableList<Int>){
            selectedInterestList = interestList
        }

        fun getInterestList() : MutableList<Int>{
            return selectedInterestList
        }

        fun setCheckFist(checkFirst : Boolean){
            this.checkFirst = checkFirst
        }

        fun getCheckFirst() : Boolean{
            return checkFirst
        }

        fun getActivityList() : MutableList<Activity>{
            return researchActivityList
        }

        fun showCloseDialog(context: Context){
            if(checkFirst){
                showStopDialog(context)
            }
            else{
                showExitDialog(context)
            }
        }

        private fun showStopDialog(context: Context){
            val deleteDialog = AppCompatDialog(context)
            val deleteLayout : LayoutInflater = LayoutInflater.from(context)
            val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup,null)

            val btnCancel : Button = deleteView.findViewById(R.id.btn_popup_cancel)
            val btnConfirm : Button = deleteView.findViewById(R.id.btn_popup_confirm)
            val txtTitle : TextView = deleteView.findViewById(R.id.txt_popup_tilte)

            txtTitle.text = "지금 설문을 중단하시면\n케어디의 서비스를 이용할 수 없습니다."

            btnCancel.setOnClickListener {
                deleteDialog.cancel()
            }

            btnConfirm.setOnClickListener {
                deleteDialog.dismiss()
            }

            deleteDialog.setContentView(deleteView)
            deleteDialog.setCanceledOnTouchOutside(false)
            deleteDialog.create()
            deleteDialog.show()
        }

        private fun showExitDialog(context: Context){
            val deleteDialog = AppCompatDialog(context)
            val deleteLayout : LayoutInflater = LayoutInflater.from(context)
            val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup,null)

            val btnCancel : Button = deleteView.findViewById(R.id.btn_popup_cancel)
            val btnConfirm : Button = deleteView.findViewById(R.id.btn_popup_confirm)
            val txtTitle : TextView = deleteView.findViewById(R.id.txt_popup_tilte)

            txtTitle.text = "설문이 완료되지 않았습니다.\n페이지를 나가시겠습니까?"

            btnCancel.setOnClickListener {
                deleteDialog.cancel()
            }

            btnConfirm.setOnClickListener {
                deleteDialog.dismiss()
            }

            deleteDialog.setContentView(deleteView)
            deleteDialog.setCanceledOnTouchOutside(false)
            deleteDialog.create()
            deleteDialog.show()
        }

    }
}