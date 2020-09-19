package info.blockstream.esplora.ui.address

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import info.blockstream.esplora.R
import info.blockstream.esplora.data.entities.ResultsItem

class AddressAdapter(
    var results: List<ResultsItem?>?,
    var onAddressClick: (transactionId: String) -> Unit
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_address, parent, false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(results?.get(position))
        holder.itemView.setOnClickListener {
            results?.get(position)?.address?.let { address -> onAddressClick(address) }
        }
    }

    override fun getItemCount(): Int {
        return results?.size ?: -1
    }

    inner class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var addressTv: AppCompatTextView = itemView.findViewById(R.id.address_tv)
        var addressIv: AppCompatImageView = itemView.findViewById(R.id.address_iv)
        fun bind(resultsItem: ResultsItem?) {
            addressTv.text = resultsItem?.address
        }
    }
}