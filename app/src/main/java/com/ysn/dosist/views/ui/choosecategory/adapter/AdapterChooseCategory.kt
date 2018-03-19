/*
 * Created by YSN Studio on 3/20/18 2:15 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 1:51 AM
 */

package com.ysn.dosist.views.ui.choosecategory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.CategoryTransaction
import kotlinx.android.synthetic.main.item_choose_category_transaction.view.*

/**
 * Created by yudisetiawan on 3/20/18.
 */
class AdapterChooseCategory(private val categories: List<CategoryTransaction>, private val listenerAdapterChooseCategory: ListenerAdapterChooseCategory) : RecyclerView.Adapter<AdapterChooseCategory.ViewHolderChooseCategory>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChooseCategory {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_choose_category_transaction, null)
        return ViewHolderChooseCategory(view)
    }

    override fun onBindViewHolder(holder: ViewHolderChooseCategory, position: Int) {
        val categoryTransaction = categories[position]
        categoryTransaction.let {
            val nameCategoryTransaction = it.name
            holder.itemView.text_view_name_item_choose_category_transaction.text = nameCategoryTransaction

            when {
                nameCategoryTransaction.equals("food drink", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_local_dining_white_24dp)
                nameCategoryTransaction.equals("shopping", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_shopping_basket_white_24dp)
                nameCategoryTransaction.equals("salary", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_credit_card_white_24dp)
                nameCategoryTransaction.equals("lifestyle", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_beach_access_white_24dp)
                nameCategoryTransaction.equals("health", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_local_hospital_white_24dp)
                nameCategoryTransaction.equals("travelling", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_flight_white_24dp)
                nameCategoryTransaction.equals("other", true) ->
                    holder.itemView.image_view_item_choose_category_transaction.setImageResource(R.drawable.ic_attach_money_white_24dp)
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolderChooseCategory(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.relative_layout_container_item_choose_category_transaction.setOnClickListener {
                listenerAdapterChooseCategory.onClickItem(categories[adapterPosition])
            }
        }
    }

    interface ListenerAdapterChooseCategory {

        fun onClickItem(chooseCategoryTransaction: CategoryTransaction)

    }

}