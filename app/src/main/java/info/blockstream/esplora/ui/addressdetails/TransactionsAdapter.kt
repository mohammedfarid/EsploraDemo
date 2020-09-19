package info.blockstream.esplora.ui.addressdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import info.blockstream.esplora.R
import info.blockstream.esplora.data.entities.Transaction

class TransactionsAdapter(var results: List<Transaction>?, var context: Context?) :
    RecyclerView.Adapter<TransactionsAdapter.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(results?.get(position))

    }

    override fun getItemCount(): Int {
        return results?.size ?: -1
    }

    inner class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bg: LinearLayoutCompat = itemView.findViewById(R.id.bg)
        var txid: AppCompatTextView = itemView.findViewById(R.id.txid_tv)
        var valueTv: AppCompatTextView = itemView.findViewById(R.id.value_tv)
        var feeTv: AppCompatTextView = itemView.findViewById(R.id.fee_tv)
        var sizeTv: AppCompatTextView = itemView.findViewById(R.id.size_tv)
        fun bind(resultsItem: Transaction?) {
            txid.text = resultsItem?.txid
            var value = 0
            resultsItem?.vin?.forEach {
                value += (it?.prevout?.value ?: 0)
            }
            valueTv.text = "${value.toDouble() / 100000000} tBTC"
            sizeTv.text = "${resultsItem?.size} B"
            feeTv.text = "${resultsItem?.fee?.toDouble()?.div(100000000)} tBTC"
            if (resultsItem?.status?.confirmed == true) {
                bg.background = context?.let { ContextCompat.getDrawable(it, R.drawable.bg_trans) }
            } else {
                bg.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.bg_trans_uncofirm) }
            }
        }
    }

    fun replaceAll(resultsNew: List<Transaction>?){
        results = resultsNew
        notifyDataSetChanged()
    }
}