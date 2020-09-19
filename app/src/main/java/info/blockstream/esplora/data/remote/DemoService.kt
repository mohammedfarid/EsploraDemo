package info.blockstream.esplora.data.remote

import info.blockstream.esplora.data.entities.Transaction
import info.blockstream.esplora.data.entities.TransactionInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DemoService {
    @GET("address/{address}")
    suspend fun getTransactionInfo(@Path("address") address: String): Response<TransactionInfo>

    @GET("address/{address}/txs")
    suspend fun getTransactions(@Path("address") address: String): Response<List<Transaction>>

    @GET("address/{address}/txs/chain/{last_seen_txid}")
    suspend fun getAllTransactions(
        @Path("address") address: String,
        @Path("last_seen_txid") lastSeenTxid: String
    ): Response<List<Transaction>>
}