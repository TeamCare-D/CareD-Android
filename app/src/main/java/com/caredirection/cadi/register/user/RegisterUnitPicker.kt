package com.caredirection.cadi.register.user

import com.super_rabbit.wheel_picker.WheelAdapter

class RegisterUnitPicker : WheelAdapter {

    //get item value based on item position in wheel
    override fun getValue(position: Int): String {
        if (position == 0)
            return "Today"

        if (position == -1)
            return "Yesterday"

        if (position == 1)
            return "Tomorrow"

        return ""
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
        return Integer.MAX_VALUE
    }

    //return the minimum index
    override fun getMinIndex(): Int {
        return Integer.MIN_VALUE
    }


}
