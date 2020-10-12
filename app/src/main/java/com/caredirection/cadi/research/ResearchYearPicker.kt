package com.caredirection.cadi.research

import com.super_rabbit.wheel_picker.WheelAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResearchYearPicker : WheelAdapter {
    //get item value based on item position in wheel
    override fun getValue(position: Int): String {

        return (position + 1990).toString()
    }

    //get item position based on item string value
    override fun getPosition(vale: String): Int {
        return 0
    }

    //return a string with the approximate longest text width, for supporting WRAP_CONTENT
    override fun getTextWithMaximumLength(): String {
        return "Mmm 00, 0000"
    }

    //return the maximum index
    override fun getMaxIndex(): Int {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        val formatted = current.format(formatter)

        return (formatted.toInt() - 1990)
    }

    //return the minimum index
    override fun getMinIndex(): Int {
        return -80
    }
}