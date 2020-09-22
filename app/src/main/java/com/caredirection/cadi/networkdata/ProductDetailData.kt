package com.caredirection.cadi.networkdata

data class ProductDetailData(
    val status: Int,
    val message: String,
    val data: ProductDetailList
)

data class ProductDetailList(
    val product_image_key: String,
    val product_is_import: String,
    val product_low_price: Int,
    val product_efficacy: List<String>,
    val product_quantity: Int,
    val product_monthly_price: Int,
    val product_vitamin_mineral_graph_size: Int,
    val product_certification: List<ProductCertificationData>,
    val product_vitamin_mineral_graph: List<ProductDetailGraph>,
    val store_info: List<StoreInfoData>,
    val product_maintain_month: Int,
    val product_suggested_use: String,
    val product_capsule_info: String,
    val brand_name: String,
    val product_package: String,
    val product_name: String,
    val product_unit: String,
    val product_allergy: List<String>,
    val product_functional_graph_size: Int,
    val different_quantity_product: List<DifferentQuantityProductData>,
    val product_criterion: List<String>,
    val product_warnings: String,
    val product_functional_graph: List<ProductFunctionalGraphData>

)
data class ProductCertificationData(
    val certification_name: String,
    val certification_image_key: String,
    val certification_description: String
)
data class ProductDetailGraph(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_sub_name: List<String>,
    val graph_original_percentage: Int,
    val graph_change_percentage: Int,
    val upper_amount: String,
    val recommended_amount: String,
    val product_ingredient_value: String,
    val product_ingredient_percentage: Int,
    val isProductAmountProper: String,
    val expected_intake_value: String,
    val expected_intake_percentage: Int,
    val product_increase_value: Int,
    val isExpectedAmountProper: String
)
data class StoreInfoData(
    val store_name: String,
    val store_img_key: String,
    val store_product_price: Int,
    val store_link: String
)
data class DifferentQuantityProductData(
    val product_idx: Int,
    val product_quantity: Int,
    val product_unit: String
)
data class ProductFunctionalGraphData(
    val ingredient_idx: Int,
    val ingredient_name: String,
    val ingredient_sub_name: List<String>,
    val graph_original_percentage: Int,
    val graph_change_percentage: Int,
    val upper_amount: String,
    val recommended_amount: String,
    val product_ingredient_value: String,
    val product_ingredient_percentage: Int,
    val isProductAmountProper: String,
    val expected_intake_value: String,
    val expected_intake_percentage: Int,
    val product_increase_value: Int,
    val isExpectedAmountProper: String
)
