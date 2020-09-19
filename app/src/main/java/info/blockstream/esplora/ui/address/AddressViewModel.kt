package info.blockstream.esplora.ui.address

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import info.blockstream.esplora.data.repository.DemoRepository
import info.blockstream.esplora.utils.Resource
import kotlinx.coroutines.Dispatchers

class AddressViewModel @ViewModelInject constructor(
    private val repository: DemoRepository
) : ViewModel() {

    fun getAddress(context: Context?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val result = repository.getAddress(context)
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