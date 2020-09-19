package info.blockstream.esplora.data.entities

data class AddressList(
    val results: List<ResultsItem?>? = null
)

data class ResultsItem(
    val address: String? = null
)

