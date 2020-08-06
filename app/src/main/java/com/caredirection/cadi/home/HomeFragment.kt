package com.caredirection.cadi.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.home.homecare.HomeCareRvAdapter
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home), AdapterView.OnItemSelectedListener {
    private lateinit var rvHomeMyCareRvAdapter: HomeCareRvAdapter
    private lateinit var rvHomeCareSimilarRvAdapter: HomeCareRvAdapter
    private val listData = ArrayList<BarEntry>()
    private lateinit var xLabelIngredients: Array<String>

    val formatter = object : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {

            return xLabelIngredients[value.toInt()]

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_graph,
                android.R.layout.simple_dropdown_item_1line
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                spinner_home_graph.adapter = adapter

            }
        }

        spinner_home_graph.setSelection(0)
        spinner_home_graph.onItemSelectedListener = this


        xLabelIngredients =
            arrayOf("비타민", "A", "B", "C", "D", "E", "A3", "B1", "C2", "D3", "E4")
        dummyChartListData()
        initLineChart()
        drawChart(listData,xLabelIngredients)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }


    private fun initLineChart() {
        // 그래프 기본 설정
        val xAxis = chart_home_vitamin.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM // x축 위치
        xAxis.granularity = 1f //label 사이 간격
        xAxis.labelCount=11

        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)


        val rightYAxis = chart_home_vitamin.axisRight
        rightYAxis.isEnabled = false

        val leftYAxis = chart_home_vitamin.axisLeft
        leftYAxis.axisMaximum = 120f
        leftYAxis.axisMinimum = 0f
        leftYAxis.granularity = 20f // label 써주는 간격 조정
        leftYAxis.setDrawGridLines(false) // false 해줘야 뒤에 모눈종이 같은게 없어져요
        leftYAxis.setDrawAxisLine(false)
        leftYAxis.setDrawLabels(false) //label 사용 여부

        val upperLimitLine = LimitLine(100f, "")
        initializeLimitLine(upperLimitLine, ContextCompat.getColor(context!!, R.color.colorPointRed))

        val lowerLimitLine = LimitLine(30f, "")
        initializeLimitLine(lowerLimitLine, ContextCompat.getColor(context!!, R.color.colorPointBlue))

        leftYAxis.addLimitLine(upperLimitLine)
        leftYAxis.addLimitLine(lowerLimitLine)


        chart_home_vitamin.legend.isEnabled = false
        chart_home_vitamin.description.isEnabled=false
        chart_home_vitamin.setVisibleXRange(6f, 11f) // X에 그려줄 최소, 최대 단위 정하기
        chart_home_vitamin.animateY(1000) //세로축 에니메이션


    }

    private fun initializeLimitLine(line: LimitLine, color: Int) {
        line.lineWidth = 3f
        line.enableDashedLine(50f, 20f, 0f)
        line.lineColor = color
        line.textSize = 10f
    }

    private fun dummyChartListData() {
        listData.add(BarEntry(0f, 130f))
        listData.add(BarEntry(1f, 20f))
        listData.add(BarEntry(2f, 60f))
        listData.add(BarEntry(3f, 80f))
        listData.add(BarEntry(4f, 120f))
        listData.add(BarEntry(5f, 40f))
        listData.add(BarEntry(6f, 55f))
        listData.add(BarEntry(7f, 20f))
        listData.add(BarEntry(8f, 60f))
        listData.add(BarEntry(9f, 80f))
        listData.add(BarEntry(10f, 120f))
    }

    private fun drawChart(listData: ArrayList<BarEntry>,xLabelIngredients:Array<String>){
        val dataSet = BarDataSet(listData,"")

        val listColor = ArrayList<Int>()

        listData.forEach {
            if (it.y > 100.0f || it.y < 30.0f)
                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointRed))
            else
                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointBlue))
        }
        dataSet.colors=listColor


        dataSet.valueTextColor=ContextCompat.getColor(context!!, R.color.colorPointBlue)
        dataSet.setDrawValues(false)

        val lineData = BarData(dataSet)
        chart_home_vitamin.data = lineData

        val xAxis=chart_home_vitamin.xAxis
        xAxis.valueFormatter=formatter

        chart_home_vitamin.invalidate()

    }


class HomeFragment : Fragment(R.layout.fragment_home) {
}