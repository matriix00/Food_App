package com.example.foodapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.models.MealsItems
import com.example.foodapp.models.Recipe
import kotlinx.android.synthetic.main.dish_item_main_category.view.*
import kotlinx.android.synthetic.main.dish_item_sub_category.view.*
import kotlinx.android.synthetic.main.dish_item_sub_category.view.img_dish
import kotlinx.android.synthetic.main.dish_item_sub_category.view.tv_dish

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>() {
    var listener: SubCategoryAdapter.OnItemClickListener?=null

    var subCategoryList  = ArrayList<MealsItems>()
    var mContext :Context?=null
    class SubCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(arrData:List<MealsItems>){

        this.subCategoryList = arrData as ArrayList<MealsItems>
    }
    fun setClickListener(mlistener: SubCategoryAdapter.OnItemClickListener){
        listener=mlistener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        mContext = parent.context

        return SubCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dish_item_sub_category,parent,false))
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.itemView.tv_dish.text = subCategoryList[position].strmeal
        Glide.with(mContext!!).load(subCategoryList[position].strmealthumb).into(holder.itemView.img_dish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(subCategoryList[position].idmeal)
        }
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }
    interface OnItemClickListener{
        fun onClicked(id: String)
    }
}