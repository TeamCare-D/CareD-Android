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

    fun getDetailList() : List<RvResearchListItem>{
        return listOf(
            RvResearchListItem(
                itemIdx = 0,
                item = "없습니다"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            ),
            RvResearchListItem(
                itemIdx = 0,
                item = "질병질병질병1"
            )
        )
    }
}