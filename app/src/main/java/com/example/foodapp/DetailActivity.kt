package com.example.foodapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foodapp.models.Category
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealResponse
import com.example.retrofit.GetDataServiceInterface
import com.example.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity() {

    var youLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var id = intent.getStringExtra("id")

        getSpecificItem(id!!)
        imagebtnback.setOnClickListener{
            finish()
        }
        btnyoutube.setOnClickListener{
            if (youLink != ""){
                val uri :Uri = Uri.parse(youLink)
                val intent = Intent(Intent.ACTION_VIEW,uri)
                startActivity(intent)
            }
        }

    }
    fun getSpecificItem(id :String){
        val service = RetrofitClientInstance.getInstance().create(GetDataServiceInterface::class.java)
        val call = service.getSpecificMeal(id)
        call.enqueue(object :Callback<MealResponse>{
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {

                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(imgitem)
                tvcategory.text = response.body()!!.mealsEntity[0].strmeal
                var ing = "${response.body()!!.mealsEntity[0].stringredient1}  ${response.body()!!.mealsEntity[0].strmeasure1}\n"+
                "${response.body()!!.mealsEntity[0].stringredient2}  ${response.body()!!.mealsEntity[0].strmeasure2}\n"+
                "${response.body()!!.mealsEntity[0].stringredient3}  ${response.body()!!.mealsEntity[0].strmeasure3}\n"+
                "${response.body()!!.mealsEntity[0].stringredient4}  ${response.body()!!.mealsEntity[0].strmeasure4}\n"+
                "${response.body()!!.mealsEntity[0].stringredient5}  ${response.body()!!.mealsEntity[0].strmeasure5}\n"+
                "${response.body()!!.mealsEntity[0].stringredient6}  ${response.body()!!.mealsEntity[0].strmeasure6}\n"+
                "${response.body()!!.mealsEntity[0].stringredient7}  ${response.body()!!.mealsEntity[0].strmeasure7}\n"+
                "${response.body()!!.mealsEntity[0].stringredient8}  ${response.body()!!.mealsEntity[0].strmeasure8}\n"+
                "${response.body()!!.mealsEntity[0].stringredient9}  ${response.body()!!.mealsEntity[0].strmeasure9}\n"+
                "${response.body()!!.mealsEntity[0].stringredient10}  ${response.body()!!.mealsEntity[0].strmeasure10}\n"+
                "${response.body()!!.mealsEntity[0].stringredient11}  ${response.body()!!.mealsEntity[0].strmeasure11}\n"+
                "${response.body()!!.mealsEntity[0].stringredient12}  ${response.body()!!.mealsEntity[0].strmeasure12}\n"+
                "${response.body()!!.mealsEntity[0].stringredient13}  ${response.body()!!.mealsEntity[0].strmeasure13}\n"+
                "${response.body()!!.mealsEntity[0].stringredient14}  ${response.body()!!.mealsEntity[0].strmeasure14}\n"+
                "${response.body()!!.mealsEntity[0].stringredient15}  ${response.body()!!.mealsEntity[0].strmeasure15}"

                tvingredint.text = ing
                tvinstructions.text = response.body()!!.mealsEntity[0].strinstructions

                if(response.body()!!.mealsEntity[0].stryoutube!=null){
                    youLink = response.body()!!.mealsEntity[0].stryoutube
                }else{
                    btnyoutube.visibility = View.GONE
                }


            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity,"Something went wrong !",Toast.LENGTH_LONG).show()
            }

        })
    }


}