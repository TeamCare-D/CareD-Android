package com.caredirection.cadi.register.user.ingredient.vitamin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterVitaminList
import kotlinx.android.synthetic.main.fragment_register_vitamin.*

class RegisterVitaminFragment : Fragment(){
    private lateinit var registerVitaminAdapter: RegisterVitaminAdapter
    private var dummyRegisterVitaminList = DummyRegisterVitaminList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vitaminListInit()
    }

    private fun vitaminListInit(){
        registerVitaminAdapter =
            RegisterVitaminAdapter(
                context
            )

        rv_register_vitamin_list.adapter = registerVitaminAdapter

        rv_register_vitamin_list.layoutManager = LinearLayoutManager(context)

        registerVitaminAdapter.data = dummyRegisterVitaminList.getRegisterVitaminList()

        registerVitaminAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_vitamin, container, false)
    }
}