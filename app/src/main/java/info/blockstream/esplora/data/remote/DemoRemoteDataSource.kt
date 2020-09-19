package info.blockstream.esplora.data.remote

import javax.inject.Inject

class DemoRemoteDataSource @Inject constructor(
    private val demoService: DemoService
) : BaseDataSource() {

    //    suspend fun getLastTenBlocks() = getResult { demoService.getLastTenBlocks() }
//    suspend fun getMoreTenBlocks(id: String) = getResult { demoService.getMoreTenBlocks(id) }
    suspend fun getTransactions(address: String) =
        getResult { demoService.getTransactions(address) }

    suspend fun getTransactionInfo(address: String) =
        getResult { demoService.getTransactionInfo(address) }

    suspend fun getAllTransactions(address: String, lastSeenTxid: String) =
        getResult { demoService.getAllTransactions(address, lastSeenTxid) }
}