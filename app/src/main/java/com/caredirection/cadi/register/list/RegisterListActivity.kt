package com.caredirection.cadi.register.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.MainActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterList
import com.caredirection.cadi.register.search.RegisterSearchActivity
import kotlinx.android.synthetic.main.activity_register_list.*

class RegisterListActivity : AppCompatActivity() {

    private lateinit var registerListAdapter: RegisterListAdapter
    private var dummyRegisterList = DummyRegisterList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_list)

        setStatusBarTransparent()

        initRegisterList()
        makeListener()
    }

    private fun initRegisterList(){
        registerListAdapter = RegisterListAdapter(this)

        rv_register_list.adapter = registerListAdapter

        rv_register_list.layoutManager = LinearLayoutManager(this)

        registerListAdapter.data = dummyRegisterList.getRegisterList()

        registerListAdapter.notifyDataSetChanged()

        checkNextButton()
    }

    private fun makeListener(){
        setNextClickListener()
        setBackClickListener()
        setCloseClickListener()
        setAddClickListener()
    }

    private fun setBackClickListener(){
        btn_register_list_back.setOnClickListener {
            finish()
        }
    }

    private fun setCloseClickListener(){
        btn_register_list_close.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)

            startActivity(homeIntent)
        }
    }

    private fun setAddClickListener(){
        btn_register_list_add.setOnClickListener {
            val searchIntent = Intent(this,RegisterSearchActivity::class.java)

            startActivity(searchIntent)
        }
    }

    private fun setNextClickListener(){
        btn_register_list_next.setOnClickListener {
            val completeIntent = Intent(this,RegisterListCompleteActivity::class.java)

            startActivity(completeIntent)
        }
    }

    private fun checkNextButton(){
        btn_register_list_next.isEnabled = false

        if(registerListAdapter.itemCount > 0){
            btn_register_list_next.isEnabled = true
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_list.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}