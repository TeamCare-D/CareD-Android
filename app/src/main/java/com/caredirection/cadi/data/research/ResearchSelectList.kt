package com.caredirection.cadi.data.research

class ResearchSelectList {

    companion object{
        private var selectedDiseaseList = mutableListOf<Int>()
        private var selectedMedicineList = mutableListOf<Int>()
        private var selectedAllergyList = mutableListOf<Int>()
        private var selectedInterestList = mutableListOf<Int>()

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
    }
}