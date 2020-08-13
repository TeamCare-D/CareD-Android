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

    private val formatter = object : ValueFormatter() {
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

        dummyChartLabel()
        dummyChartListData()
        initBarChart()
        drawChart(listData,xLabelIngredients)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }


    private fun initBarChart() {
        // 그래프 기본 설정
        val xAxis = chart_home_vitamin.xAxis

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM // x축 위치 : 데이터의 위치를 아래로
            granularity = 1f // x축 간격
            labelCount = 11 // label 개수
            setDrawAxisLine(false) // x축
            setDrawGridLines(false) // x축 기준선
        }

        val yAxisRight = chart_home_vitamin.axisRight
        yAxisRight.apply {
            isEnabled = false
        }

        val yAxisLeft = chart_home_vitamin.axisLeft
        yAxisLeft.apply {
            axisMaximum = 120f // y축 최고값
            axisMinimum = 0f // y축 최저값
            granularity = 20f // y축 간격
            setDrawAxisLine(false) // y축
            setDrawGridLines(false) // y축 기준선
            setDrawLabels(false) // label 사용 x
        }

        chart_home_vitamin.apply {
            legend.isEnabled = false
            description.isEnabled = false
            setVisibleXRange(6f, 11f) // X에 그려줄 최소, 최대 단위 정하기
            animateY(1000) //세로축 에니메이션
        }

        val upperLimitLine = LimitLine(90f, "")
        initLimitLine(upperLimitLine, ContextCompat.getColor(context!!, R.color.colorPointRed))

        val lowerLimitLine = LimitLine(30f, "")
        initLimitLine(lowerLimitLine, ContextCompat.getColor(context!!, R.color.colorPointGray))

        yAxisLeft.addLimitLine(upperLimitLine)
        yAxisLeft.addLimitLine(lowerLimitLine)
    }

    private fun initLimitLine(line: LimitLine, color: Int) {
        line.lineWidth = 1f // 두께
        line.enableDashedLine(5f, 10f, 0f) // 길이, 간격
        line.lineColor = color
        line.textSize = 10f
    }

    private fun dummyChartLabel(){
        xLabelIngredients =
            arrayOf("비타민A", "비타민D", "비타민C", "비타민B6", "비타민B12", "E", "A3", "B1", "C2", "D3", "E4")
    }

    private fun dummyChartListData() {
        listData.add(BarEntry(0f, floatArrayOf(90f,20f)))
        listData.add(BarEntry(1f, floatArrayOf(90f,10f)))
        listData.add(BarEntry(2f, 20f))
        listData.add(BarEntry(3f, 80f))
        listData.add(BarEntry(4f, floatArrayOf(90f,30f)))
        listData.add(BarEntry(5f, 40f))
        listData.add(BarEntry(6f, 55f))
        listData.add(BarEntry(7f, 20f))
        listData.add(BarEntry(8f, 60f))
        listData.add(BarEntry(9f, 80f))
        listData.add(BarEntry(10f, floatArrayOf(90f,10f)))
    }

    private fun drawChart(listData: ArrayList<BarEntry>,xLabelIngredients:Array<String>){
        val dataSet = BarDataSet(listData,"")

        val listColor = ArrayList<Int>()



        listData.forEach {
            if(it.y > 90.0f){
                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointBlue))
                listColor.add(ContextCompat.getColor(context!!, R.color.colorPointRed))
            }
            else{
                when{
                    it.y < 90.0f && it.y > 30.0f -> listColor.add(ContextCompat.getColor(context!!, R.color.colorPointBlue))
                    it.y < 30.0f -> listColor.add(ContextCompat.getColor(context!!, R.color.colorPointGray))
                }
            }
        }

        dataSet.apply {
            colors=listColor
            valueTextColor=ContextCompat.getColor(context!!, R.color.colorPointBlue)
            setDrawValues(false)
        }

        val lineData = BarData(dataSet)
        lineData.barWidth = 0.15f
        chart_home_vitamin.data = lineData

        val xAxis=chart_home_vitamin.xAxis
        xAxis.valueFormatter=formatter

        chart_home_vitamin.invalidate()

    }

}