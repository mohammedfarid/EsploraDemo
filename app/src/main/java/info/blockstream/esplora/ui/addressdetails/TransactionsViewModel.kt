package info.blockstream.esplora.ui.addressdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import info.blockstream.esplora.data.repository.DemoRepository
import info.blockstream.esplora.utils.Resource
import kotlinx.coroutines.Dispatchers

class TransactionsViewModel @ViewModelInject constructor(
    private val repository: DemoRepository
) : ViewModel() {
    fun getTransactions(address: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val result = repository.getTransactions(address)
        try {
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null, message = exception.message
                        ?: "Error Occurred!"
                )
            )
        }
    }

    fun getTransactionInfo(address: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val result = repository.getTransactionInfo(address)
        try {
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null, message = exception.message
                        ?: "Error Occurred!"
                )
            )
        }
    }

    fun getAllTransactions(address: String, lastSeenTxid: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val result = repository.getAllTransactions(address, lastSeenTxid)
        try {
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null, message = exception.message
                        ?: "Error Occurred!"
                )
            )
        }
    }
}