package com.caredirection.cadi.data.research

import com.caredirection.cadi.data.network.ResearchItemData

class ResearchDetailList{

    companion object{
        private lateinit var researchDetailList: ResearchItemData

        fun setResearchList(research: ResearchItemData){
            researchDetailList = research
        }

        fun getResearchList() : ResearchItemData {
            return researchDetailList
        }
    }
}