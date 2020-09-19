package info.blockstream.esplora.data.entities

import com.google.gson.annotations.SerializedName

data class TransactionInfo(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("chain_stats")
    val chainStats: ChainStats? = null,

    @field:SerializedName("mempool_stats")
    val mempoolStats: MempoolStats? = null
)

data class MempoolStats(

    @field:SerializedName("spent_txo_count")
    val spentTxoCount: Int? = null,

    @field:SerializedName("tx_count")
    val txCount: Int? = null,

    @field:SerializedName("funded_txo_count")
    val fundedTxoCount: Int? = null,

    @field:SerializedName("spent_txo_sum")
    val spentTxoSum: Int? = null,

    @field:SerializedName("funded_txo_sum")
    val fundedTxoSum: Int? = null
)

data class ChainStats(

    @field:SerializedName("spent_txo_count")
    val spentTxoCount: Int? = null,

    @field:SerializedName("tx_count")
    val txCount: Int? = null,

    @field:SerializedName("funded_txo_count")
    val fundedTxoCount: Int? = null,

    @field:SerializedName("spent_txo_sum")
    val spentTxoSum: Int? = null,

    @field:SerializedName("funded_txo_sum")
    val fundedTxoSum: Int? = null
)
