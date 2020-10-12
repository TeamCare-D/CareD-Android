package com.caredirection.cadi.product.detail


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.CareCategoryRvAdapter
import com.caredirection.cadi.data.network.RegisterTakeProductData
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.ProductDetailData
import com.caredirection.cadi.networkdata.ProductDetailList
import com.caredirection.cadi.networkdata.ProductLikeData
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_product_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DetailActivity : AppCompatActivity(), pickerCallback {

    private lateinit var productDetailViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        pickerSetting()

        val product_idx = intent.getIntExtra("product_idx", 0)

        Log.d("이거는", product_idx.toString())
        productLikeSetting(10)
        productRegister()

        productDetailSetting(product_idx)
    }

    fun ViewPagerSetting(productDetailData: ProductDetailList) {

        productDetailViewPager = findViewById(R.id.view_pager_product_detail)
        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        val fragmentContent = FragmentContent()
        fragmentContent.productDetailData = productDetailData

        val fragmentIntake = FragmentIntake()
        fragmentIntake.productDetailData = productDetailData

        adapter.items.add(fragmentContent)
        adapter.items.add(fragmentIntake)

        productDetailViewPager.adapter = adapter

        tab_layout_product_detail.setupWithViewPager(productDetailViewPager)
        val menu = arrayListOf("제품 함량", "예상 섭취 변화량")
        for (i in 0..menu.size) {
            tab_layout_product_detail.getTabAt(i)?.text = menu[i]
        }
    }

    fun pickerSetting() {

        txt_product_detail_amount.setOnClickListener {
            val bottomSheetFragment = bottomSheetFragment()
            val bottomView = layoutInflater.inflate(R.layout.view_bottom_sheet, null)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }


    }

    fun productDetailSetting(product_idx: Int) {
        val call: Call<ProductDetailData> = RequestURL.service.getProductDetail(
            product_idx = product_idx,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<ProductDetailData> {
                override fun onFailure(call: Call<ProductDetailData>, t: Throwable) {
                    Log.d("productDetailSetting onFrailure", t.toString())
                }

                override fun onResponse(
                    call: Call<ProductDetailData>,
                    response: Response<ProductDetailData>
                ) {

                    try {
                        val data = response.body()!!.data
                        Log.d("테스트임", data.toString())
                        Glide.with(baseContext).load(data.product_image_key)
                            .into(img_product_detail)

                        txt_product_detail_brand.text = data.brand_name

                        txt_product_detail_name.text = data.product_name

                        txt_product_detail_origin.text = data.product_is_import
                        txt_product_detail_maintenance_per.text = data.product_maintain_month.toString()
                        txt_product_detail_lowest_price.text = data.product_low_price.toString()
                        txt_product_detail_30_per_price.text = data.product_monthly_price.toString()




//                        if(!data.product_criterion[0].equals(null) && !data.product_criterion[0].equals("")){
//                            txt_product_detail_standard_1.visibility = View.VISIBLE
//                            txt_product_detail_standard_1.text = data.product_criterion[0]
//                        }
//                        if(!data.product_criterion[1].equals(null) && !data.product_criterion[1].equals("")){
//                            txt_product_detail_standard_2.visibility = View.VISIBLE
//                            txt_product_detail_standard_2.text = data.product_criterion[1]
//                        }
//
//                        if(!data.product_criterion[2].equals(null) && !data.product_criterion[2].equals("")){
//                            txt_product_detail_standard_3.visibility = View.VISIBLE
//                            txt_product_detail_standard_3.text = data.product_criterion[2]
//                        }
                        txt_product_detail_standard_1.text = data.product_criterion[0]
                        txt_product_detail_standard_2.text = data.product_criterion[1]
                        txt_product_detail_standard_3.text = data.product_criterion[2]

                        productEfficacySetting(data.product_efficacy)

                        ViewPagerSetting(data)
                    } catch (e: Exception) {
                        Log.d("에러잡기", e.toString())
                    }
                }
            }
        )
    }

    fun productEfficacySetting(product_efficacy: List<String>) {
        val careCategoryAdapter = CareCategoryRvAdapter()
        careCategoryAdapter.items.addAll(product_efficacy)
        rv_product_detail_care_category.adapter = careCategoryAdapter

    }

    fun productLikeSetting(product_idx: Int) {
        btn_product_detail_like.setOnClickListener {
            val call: Call<ProductLikeData> = RequestURL.service.postProductLike(
                product_idx = 10,
                token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
            )
            call.enqueue(
                object : Callback<ProductLikeData> {
                    override fun onFailure(call: Call<ProductLikeData>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<ProductLikeData>,
                        response: Response<ProductLikeData>
                    ) {
                        Toast.makeText(baseContext, response.message().toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    fun productRegister() {
        btn_product_detail_product_register.setOnClickListener {
            val call: Call<RegisterTakeProductData> = RequestURL.service.postTakeProduct(
                product_idx = 10,
                token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
            )
            call.enqueue(
                object : Callback<RegisterTakeProductData> {
                    override fun onFailure(call: Call<RegisterTakeProductData>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<RegisterTakeProductData>,
                        response: Response<RegisterTakeProductData>
                    ) {
                        Toast.makeText(baseContext, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    override fun callback(per: String) {
        txt_product_detail_amount.text = per
    }
}


interface pickerCallback {
    fun callback(per: String)
}