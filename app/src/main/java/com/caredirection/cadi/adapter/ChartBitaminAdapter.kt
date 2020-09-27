package com.caredirection.cadi.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.GraphBitamin

class ChartBitaminAdapter(private val context: Context): RecyclerView.Adapter<ChartBitaminAdapter.ChartBitaminHolder>() {

    var items = mutableListOf<GraphBitamin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartBitaminHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_chart, parent, false)
        return ChartBitaminHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChartBitaminHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ChartBitaminHolder(view: View): RecyclerView.ViewHolder(view){

        val bar_rv_item_chart1: TextView = view.findViewById(R.id.bar_rv_item_chart_1)
        val bar_rv_item_chart2: TextView = view.findViewById(R.id.bar_rv_item_chart_2)
        val bar_rv_item_chart_3: TextView = view.findViewById(R.id.bar_rv_item_chart_3)
        val txt_rv_item_name: TextView = view.findViewById(R.id.txt_rv_item_chart_name)

        fun bind(item: GraphBitamin){
            var h = item.ingredient_percentage * 1.7 * 3

//            if(item.height == 0){
//                bar_rv_item_chart1.layoutParams.height = 0
//                bar_rv_item_chart2.layoutParams.height = 0
//                bar_rv_item_chart_3.layoutParams.height = 0
//                return
//            }


            if(item.ingredient_percentage < 30){
                //bar_rv_item_chart1.layoutParams.height = h.toInt()
                bar_rv_item_chart1.layoutParams.height = 90
                bar_rv_item_chart1.visibility = View.VISIBLE
                bar_rv_item_chart1.background = ContextCompat.getDrawable(context, R.drawable.chart_carolina_blue_4)

                //bar_rv_item_chart1.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)

            }
            else if(item.ingredient_percentage in 30..100){
                bar_rv_item_chart1.layoutParams.height = 153
                bar_rv_item_chart2.layoutParams.height = (h - 153).toInt()
                bar_rv_item_chart2.background = ContextCompat.getDrawable(context, R.drawable.chart_blue_4)
//                bar_rv_item_chart1.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)
//                bar_rv_item_chart2.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)

            }
            else if(item.ingredient_percentage > 100){
                bar_rv_item_chart1.layoutParams.height = 153
                bar_rv_item_chart2.layoutParams.height = 510 - 153
                bar_rv_item_chart_3.layoutParams.height = (h-510).toInt()
                bar_rv_item_chart_3.background = ContextCompat.getDrawable(context, R.drawable.chart_yellow_4)

//                bar_rv_item_chart1.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)
//                bar_rv_item_chart2.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)
//                bar_rv_item_chart_3.animation = AnimationUtils.loadAnimation(context, R.anim.ani_test)


            }

            txt_rv_item_name.text = item.ingredient_name
            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.ani_chart)
        }

    }
}