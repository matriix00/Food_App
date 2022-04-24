package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.adapters.MainCategoryAdapter
import com.example.foodapp.adapters.SubCategoryAdapter
import com.example.foodapp.db.RecipeDatabase
import com.example.foodapp.models.CategoryItems
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealsItems
import com.example.foodapp.models.Recipe
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDB()
        mainCategoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedSub)



    }
    private val onClicked = object :MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDB(categoryName)
        }
    }
    private val onClickedSub = object :SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }
    private fun getDataFromDB(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                getMealDataFromDB(arrMainCategory[0].strCategory)
                mainCategoryAdapter.setData(arrMainCategory)
                main_category_rv.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                main_category_rv.adapter = mainCategoryAdapter

            }
        }
    }
    private fun getMealDataFromDB(categoryName:String){
        cat_name.text = "$categoryName category"
        launch {
            this.let {
                var meal = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMeal(categoryName)
                arrSubCategory = meal as ArrayList<MealsItems>
                arrSubCategory.reverse()
                subCategoryAdapter.setData(arrSubCategory)
                sub_category_rv.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                sub_category_rv.adapter = subCategoryAdapter

            }
        }
    }
}