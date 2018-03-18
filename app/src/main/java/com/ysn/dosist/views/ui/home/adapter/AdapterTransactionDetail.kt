package com.ysn.dosist.views.ui.home.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R
import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.DetailTransaction
import kotlinx.android.synthetic.main.item_transaction_detail.view.*
import org.joda.time.DateTime
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yudisetiawan on 3/18/18.
 */
class AdapterTransactionDetail(private val context: Context, private val dbManager: DbManager, private var detailTransactions: List<DetailTransaction>) : RecyclerView.Adapter<AdapterTransactionDetail.ViewHolderItemTransactionDetail>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemTransactionDetail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_detail, null)
        return ViewHolderItemTransactionDetail(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItemTransactionDetail, position: Int) {
        val transaction = detailTransactions[position]
        transaction.let {
            holder.itemView.text_view_subject_item_transaction_detail.text = it.subject

            val amount = DecimalFormat("#,###").format(it.amount)
            val formatAmount: String
            when (it.type.toLowerCase()) {
                "income" -> {
                    formatAmount = context.getString(R.string.amount_format_income, amount)
                    holder.itemView.text_view_amount_item_transaction_detail.apply {
                        this.text = formatAmount
                        this.setTextColor(ContextCompat.getColor(context, R.color.colorTealLight))
                    }
                }
                "expense" -> {
                    formatAmount = context.getString(R.string.amount_format_expense, amount)
                    holder.itemView.text_view_amount_item_transaction_detail.apply {
                        this.text = formatAmount
                        this.setTextColor(ContextCompat.getColor(context, R.color.colorPinkLight))
                    }
                }
                else -> {
                    /** nothing to do in here */
                }
            }

            val formatDate = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(DateTime(it.timestamp))
            holder.itemView.text_view_date_item_transaction_detail.text = formatDate

            holder.itemView.image_view_item_category_item_transaction_detail
                    .setImageDrawable(
                            when (it.idCategory) {
                                1L -> ContextCompat.getDrawable(context, R.drawable.ic_local_dining_white_24dp)
                                2L -> ContextCompat.getDrawable(context, R.drawable.ic_shopping_basket_white_24dp)
                                3L -> ContextCompat.getDrawable(context, R.drawable.ic_credit_card_white_24dp)
                                4L -> ContextCompat.getDrawable(context, R.drawable.ic_beach_access_white_24dp)
                                5L -> ContextCompat.getDrawable(context, R.drawable.ic_local_hospital_white_24dp)
                                6L -> ContextCompat.getDrawable(context, R.drawable.ic_flight_white_24dp)
                                else -> ContextCompat.getDrawable(context, R.drawable.ic_attach_money_white_24dp)
                            }
                    )

        }
    }

    override fun getItemCount(): Int = detailTransactions.size

    fun refresh(detailTransactions: ArrayList<DetailTransaction>) {
        this.detailTransactions = detailTransactions
        notifyDataSetChanged()
    }

    inner class ViewHolderItemTransactionDetail(itemView: View) : RecyclerView.ViewHolder(itemView)

}