package com.caredirection.cadi.register.user

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.IngredientSelectList
import com.caredirection.cadi.register.user.ingredient.RegisterIngredientFragment
import kotlinx.android.synthetic.main.activity_register_product.*

class RegisterProductActivity : AppCompatActivity() {

    private lateinit var registerSelectAdapter: RegisterSelectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_product)

        setStatusBarTransparent()

        makeListener()

        checkNameEmpty()
    }

    private fun setIngredientView(){
        txt_register_ingredient_name.visibility = View.GONE
        edt_register_ingredient_content.visibility = View.GONE
        txt_register_ingredient_unit.visibility = View.GONE
        btn_register_ingredient_dropdown.visibility = View.GONE

        rv_register_product_ingredient.visibility = View.VISIBLE
    }

    fun getIngredientList(){
        setIngredientView()

        registerSelectAdapter = RegisterSelectAdapter(this)

        rv_register_product_ingredient.adapter = registerSelectAdapter

        rv_register_product_ingredient.layoutManager = LinearLayoutManager(this)

        registerSelectAdapter.data = IngredientSelectList.getSelectedIngredientList()

        registerSelectAdapter.notifyDataSetChanged()
    }

    private fun makeListener(){
        setBackClickListener()
        setIngredientMoreClickListener()
    }

    private fun setBackClickListener(){
        btn_register_product_back.setOnClickListener {
            finish()
        }
    }

    private fun setIngredientMoreClickListener(){
        txt_register_product_more.setOnClickListener {
            showIngredientDialog()
        }
    }

    private fun showIngredientDialog(){
        val fragmentManager = supportFragmentManager
        val registerIngredientFragment = RegisterIngredientFragment()

        registerIngredientFragment.show(fragmentManager,"IngredientDialog")
    }

    private fun checkNameEmpty(){
        edt_register_product_name?.addTextChangedListener(object: TextWatcher {
            var productNameLength = 0
            override fun afterTextChanged(p0: Editable?) {
                productNameLength = edt_register_product_name?.length()!!

                if(productNameLength > 0){
                    edt_register_product_name.background = resources.getDrawable(R.drawable.blue_2_line_4)
                }
                else{
                    edt_register_product_name.background = resources.getDrawable(R.drawable.gray_line_4)
                }
                checkNextButton()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
    }

    fun checkNextButton(){
        var checkEmpty = true

        btn_register_product_complete.isEnabled = false
        btn_register_product_complete.setTextColor(getColor(R.color.colorCoolGray2))

        if(edt_register_product_name.length() > 0){
            (0 until registerSelectAdapter.itemCount).forEach {
                if(registerSelectAdapter.data[it].content!!.isEmpty()){
                    checkEmpty = false
                }
            }
            if(checkEmpty){
                btn_register_product_complete.isEnabled = true
                btn_register_product_complete.setTextColor(getColor(R.color.colorWhite))
            }
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_product.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setDarkStatusBar()
    }

    // 상태바 어둡게
    private fun setDarkStatusBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
}
