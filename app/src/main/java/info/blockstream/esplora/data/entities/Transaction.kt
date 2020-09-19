package info.blockstream.esplora.data.entities

import com.google.gson.annotations.SerializedName

data class Transaction(

    @field:SerializedName("locktime")
    val locktime: Int? = null,

    @field:SerializedName("size")
    val size: Int? = null,

    @field:SerializedName("fee")
    val fee: Int? = null,

    @field:SerializedName("txid")
    val txid: String? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("vin")
    val vin: List<VinItem?>? = null,

    @field:SerializedName("version")
    val version: Int? = null,

    @field:SerializedName("vout")
    val vout: List<VoutItem?>? = null,

    @field:SerializedName("status")
    val status: Status? = null
)

data class VinItem(

    @field:SerializedName("scriptsig")
    val scriptsig: String? = null,

    @field:SerializedName("witness")
    val witness: List<String?>? = null,

    @field:SerializedName("sequence")
    val sequence: Long? = null,

    @field:SerializedName("inner_redeemscript_asm")
    val innerRedeemscriptAsm: String? = null,

    @field:SerializedName("scriptsig_asm")
    val scriptsigAsm: String? = null,

    @field:SerializedName("prevout")
    val prevout: Prevout? = null,

    @field:SerializedName("is_coinbase")
    val isCoinbase: Boolean? = null,

    @field:SerializedName("txid")
    val txid: String? = null,

    @field:SerializedName("vout")
    val vout: Int? = null
)

data class Prevout(

    @field:SerializedName("scriptpubkey_address")
    val scriptpubkeyAddress: String? = null,

    @field:SerializedName("scriptpubkey")
    val scriptpubkey: String? = null,

    @field:SerializedName("scriptpubkey_asm")
    val scriptpubkeyAsm: String? = null,

    @field:SerializedName("scriptpubkey_type")
    val scriptpubkeyType: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)

data class VoutItem(

    @field:SerializedName("scriptpubkey_address")
    val scriptpubkeyAddress: String? = null,

    @field:SerializedName("scriptpubkey")
    val scriptpubkey: String? = null,

    @field:SerializedName("scriptpubkey_asm")
    val scriptpubkeyAsm: String? = null,

    @field:SerializedName("scriptpubkey_type")
    val scriptpubkeyType: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)

data class Status(

    @field:SerializedName("block_time")
    val blockTime: Int? = null,

    @field:SerializedName("block_hash")
    val blockHash: String? = null,

    @field:SerializedName("block_height")
    val blockHeight: Int? = null,

    @field:SerializedName("confirmed")
    val confirmed: Boolean? = null
)
