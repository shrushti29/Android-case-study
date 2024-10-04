package com.target.targetcasestudy.data.local

data class DealItem(
  var id: Int,
  var title: String,
  var description: String,
  var price: String,
  var aisle: String,
  var imageUrl : String =  "https://appstorage.target.com/app-data/native-tha-images/1.jpg"
)