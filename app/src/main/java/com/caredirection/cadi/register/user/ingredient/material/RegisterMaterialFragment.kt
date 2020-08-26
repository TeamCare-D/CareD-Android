package com.caredirection.cadi.register.user.ingredient.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterMaterialList
import kotlinx.android.synthetic.main.fragment_register_material.*

class RegisterMaterialFragment : Fragment(){
    private lateinit var registerMaterialAdapter: RegisterMaterialAdapter
    private var dummyRegisterMaterialList = DummyRegisterMaterialList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        materialListInit()
    }

    private fun materialListInit(){
        registerMaterialAdapter = RegisterMaterialAdapter(context)

        rv_register_material_list.adapter = registerMaterialAdapter

        rv_register_material_list.layoutManager = LinearLayoutManager(context)

        registerMaterialAdapter.data = dummyRegisterMaterialList.getRegisterMaterialList()

        registerMaterialAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_material,container,false)
    }
}