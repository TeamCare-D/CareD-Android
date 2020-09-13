package com.caredirection.cadi.research.medicine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.RvResearchListItem

class ResearchMedicineAdapter(private val context: Context) : RecyclerView.Adapter<ResearchMedicineViewHolder>(){

    var data : List<RvResearchListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnMedicine : CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchMedicineViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        btnMedicine = view.findViewById(R.id.btn_item)

        return ResearchMedicineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchMedicineViewHolder, position: Int) {
        holder.onBind(data[position])

        btnMedicine.setOnClickListener {
            if(selectedItem.contains(data[position].itemIdx)){
                selectedItem.remove(data[position].itemIdx)
                btnMedicine.isChecked = !btnMedicine.isChecked
                //Log.d("명",data[position].productIdx.toString()+"삭제")
            }
            else{
                selectedItem.add(data[position].itemIdx)
                btnMedicine.isChecked = !btnMedicine.isChecked
                //Log.d("명",data[position].productIdx.toString()+"추가")
            }
            (context as ResearchMedicineActivity).checkNextButton()
        }
    }
}