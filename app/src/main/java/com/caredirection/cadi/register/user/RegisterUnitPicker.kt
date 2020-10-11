package com.caredirection.cadi.register.user

import com.super_rabbit.wheel_picker.WheelAdapter

class RegisterUnitPicker(units: List<String>) : WheelAdapter {
    var list = units

    //get item value based on item position in wheel
    override fun getValue(position: Int): String {
        when (list.size) {
            1 -> {
                if (position == -1)
                    return list[0]
            }

            2 -> {
                if (position == -1)
                    return list[0]

                if (position == 0)
                    return list[1]
            }
            3 -> {
                if (position == 0)
                    return list[0]

                if (position == -1)
                    return list[1]

                if (position == 1)
                    return list[2]
            }
        }

        return ""
    }

    //get item position based on item string value
    override fun getPosition(vale: String): Int {
        if(list.size == 3){
            return 0
        }
        return -1
    }

    //return a string with the approximate longest text width, for supporting WRAP_CONTENT
    override fun getTextWithMaximumLength(): String {
        return "Mmm 00, 0000"
    }

    //return the maximum index
    override fun getMaxIndex(): Int {
        return (list.size - 2)
    }

    //return the minimum index
    override fun getMinIndex(): Int {
        return -1
    }


}
