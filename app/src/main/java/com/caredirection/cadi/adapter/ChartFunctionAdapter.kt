package com.caredirection.cadi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.GraphFunction

class ChartFunctionAdapter(private val context: Context): RecyclerView.Adapter<ChartFunctionAdapter.ChartFunctionHolder>() {

    val items = mutableListOf<GraphFunction>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartFunctionHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_chart, parent, false)
        return ChartFunctionHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChartFunctionHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ChartFunctionHolder(view: View): RecyclerView.ViewHolder(view){
        val bar_rv_item_chart1: TextView = view.findViewById(R.id.bar_rv_item_chart_1)
        val bar_rv_item_chart2: TextView = view.findViewById(R.id.bar_rv_item_chart_2)
        val bar_rv_item_chart_3: TextView = view.findViewById(R.id.bar_rv_item_chart_3)
        val txt_rv_item_name: TextView = view.findViewById(R.id.txt_rv_item_chart_name)

        fun bind(item: GraphFunction){
            val h = item.ingredient_percentage * 1.7 * 3

            if(item.ingredient_percentage < 30){
                bar_rv_item_chart1.layoutParams.height = h.toInt()

                bar_rv_item_chart1.visibility = View.VISIBLE
                bar_rv_item_chart1.background = ContextCompat.getDrawable(context, R.drawable.chart_carolina_blue_4)
            }
            else if(item.ingredient_percentage in 30..100){
                bar_rv_item_chart1.layoutParams.height = 153
                bar_rv_item_chart2.layoutParams.height = (h - 153).toInt()
                bar_rv_item_chart2.background = ContextCompat.getDrawable(context, R.drawable.chart_blue_4)

            }
            else if(item.ingredient_percentage > 100){
                bar_rv_item_chart1.layoutParams.height = 153
                bar_rv_item_chart2.layoutParams.height = 510 - 153
                bar_rv_item_chart_3.layoutParams.height = (h-510).toInt()
                bar_rv_item_chart_3.background = ContextCompat.getDrawable(context, R.drawable.chart_yellow_4)


            }

            txt_rv_item_name.text = item.ingredient_name
            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.ani_chart)

        }

    }
}