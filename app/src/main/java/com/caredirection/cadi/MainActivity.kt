package com.caredirection.cadi

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.caredirection.cadi.data.research.ResearchSelectList
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
        initNavigation()

        setStatusBarTransparent()
    }

    private fun initActivity(){
        (0 until ResearchSelectList.researchActivityList.size).forEach {
            ResearchSelectList.researchActivityList[it].finish()
        }
    }

    private fun initNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navi)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNav.setupWithNavController(navController)
        bottomNav.itemIconTintList = null
    }

    // 상태바 영역 사용
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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