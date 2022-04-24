package com.example.foodapp

import android.content.Intent
import android.icu.lang.UCharacterEnums
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodapp.db.RecipeDatabase
import com.example.retrofit.GetDataServiceInterface
import com.example.foodapp.models.Category
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealsItems
import com.example.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity(),EasyPermissions.PermissionCallbacks ,EasyPermissions.RationaleCallbacks{
    private var READ_STORAGE_PERM = 123
    private val TAG = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        readStorageTask()
        btn_get_started.setOnClickListener{
            var intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun getCategories(){
        val service = RetrofitClientInstance.getInstance().create(GetDataServiceInterface::class.java)
        val call = service.getCategoriesList()
        call.enqueue(object :Callback<Category>{
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                for (arr in response.body()!!.categoryitems!!){
                    getMeal(arr.strCategory)
                }

                insertCategoryDataToRoomDB(response.body())

            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@SplashActivity,"Something went wrong !",Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getMeal(categoryName:String){
        val service = RetrofitClientInstance.getInstance().create(GetDataServiceInterface::class.java)
        val call = service.getMealsList(categoryName)
        call.enqueue(object :Callback<Meal>{
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {
                insertMealDataToRoomDB(categoryName,response.body())

            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity,"Something went wrong !",Toast.LENGTH_LONG).show()
            }

        })
    }
    fun insertMealDataToRoomDB(categoryName: String,meal: Meal?){
        launch {
            this.let {
                for (arr in meal!!.mealsItem!!){
                    var mealItem = MealsItems(
                        arr.id,
                        arr.idmeal,
                        categoryName,
                        arr.strmeal,
                        arr.strmealthumb
                    )
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().addMeal(mealItem)
                    Log.d(TAG, "insertMealDataToRoomDB: mealData"+arr.toString())
                }
                btn_get_started.visibility = View.VISIBLE
            }
        }

    }
    fun insertCategoryDataToRoomDB(category: Category?){
        launch {
            this.let {
                for (arr in category!!.categoryitems!!){
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().addCategory(arr)
                }
            }
        }

    }
    fun clearDB(){
        launch {
            this.let {
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearCatdb()

            }
        }
    }
    private fun hasReadStoragePermission():Boolean{
        return EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    private fun readStorageTask(){
        if (hasReadStoragePermission()){
            clearDB()
            getCategories()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage, ",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }
}