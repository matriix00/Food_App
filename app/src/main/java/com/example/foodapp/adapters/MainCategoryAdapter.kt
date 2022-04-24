package com.example.foodapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.models.CategoryItems
import com.example.foodapp.models.Recipe
import kotlinx.android.synthetic.main.dish_item_main_category.view.*

class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    var listener:OnItemClickListener?=null
    var mainCategoryList  = ArrayList<CategoryItems>()
    var mContext:Context?=null
    class MainCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(arrData:List<CategoryItems>){
        this.mainCategoryList = arrData as ArrayList<CategoryItems>
    }
    fun setClickListener(mlistener:OnItemClickListener){
        listener=mlistener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        mContext = parent.context
        return MainCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dish_item_main_category,parent,false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        holder.itemView.tv_dish.text = mainCategoryList[position].strCategory
        Glide.with(mContext!!).load(mainCategoryList[position].strCategoryThumb).into(holder.itemView.img_dish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(mainCategoryList[position].strCategory)
        }
    }
    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }

    override fun getItemCount(): Int {
        return mainCategoryList.size
    }
}