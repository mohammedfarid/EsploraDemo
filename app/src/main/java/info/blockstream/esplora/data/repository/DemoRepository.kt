package info.blockstream.esplora.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import info.blockstream.esplora.data.entities.AddressList
import info.blockstream.esplora.data.entities.Transaction
import info.blockstream.esplora.data.entities.TransactionInfo
import info.blockstream.esplora.data.remote.DemoRemoteDataSource
import info.blockstream.esplora.utils.JsonUtils
import info.blockstream.esplora.utils.Resource
import javax.inject.Inject

class DemoRepository @Inject constructor(private val remoteDataSource: DemoRemoteDataSource) {
    fun getAddress(context: Context?): AddressList =
        Gson().fromJson(
            JsonUtils.readJSONFromAsset(context, "addresss"),
            object : TypeToken<AddressList?>() {}.type
        )

    suspend fun getTransactions(address: String): Resource<List<Transaction>> =
        remoteDataSource.getTransactions(address)

    suspend fun getTransactionInfo(address: String): Resource<TransactionInfo> =
        remoteDataSource.getTransactionInfo(address)

    suspend fun getAllTransactions(
        address: String,
        lastSeenTxid: String
    ): Resource<List<Transaction>> = remoteDataSource.getAllTransactions(address, lastSeenTxid)
}