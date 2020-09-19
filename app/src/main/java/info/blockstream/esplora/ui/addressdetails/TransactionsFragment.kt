package info.blockstream.esplora.ui.addressdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import info.blockstream.esplora.R
import info.blockstream.esplora.utils.Status
import kotlinx.android.synthetic.main.fragment_address.loading_layout
import kotlinx.android.synthetic.main.fragment_address.toolbar
import kotlinx.android.synthetic.main.transactions_fragment.*

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    companion object {
        val TAG = TransactionsFragment::class.java.simpleName
        const val ADDRESS_KEY = "address_key"

        fun newInstants(addressId: String?) = run {
            val fragment = TransactionsFragment()
            fragment.arguments = bundleOf(
                ADDRESS_KEY to addressId
            )
            return@run fragment
        }
    }

    val transactionsViewModel: TransactionsViewModel by viewModels()

    lateinit var transactionsAdapter: TransactionsAdapter
    var addressId: String = ""

    var firstTime = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchBundle(arguments)
    }

    private fun fetchBundle(arguments: Bundle?) {
        addressId = arguments?.getString(ADDRESS_KEY, "").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.history_trans)

        transactionsViewModel.getTransactionInfo(addressId)
            .observe(viewLifecycleOwner, { resources ->
                when (resources.status) {
                    Status.LOADING -> {
                        loading_layout.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        loading_layout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        loading_layout.visibility = View.GONE
                        Log.d(
                            TAG,
                            "${resources.data?.data?.chainStats?.txCount ?: 0.plus(resources.data?.data?.mempoolStats?.txCount ?: 0)}"
                        )
                        val txCount = (resources.data?.data?.chainStats?.txCount
                            ?: 0) + (resources.data?.data?.mempoolStats?.txCount ?: 0)
                        getAllTransactions(addressId, txCount)
                    }
                }
            })
    }

    private fun getAllTransactions(addressId: String, txCount: Int) {
        transactionsViewModel.getAllTransactions(addressId, txCount.toString())
            .observe(viewLifecycleOwner, { resources ->
                when (resources.status) {
                    Status.LOADING -> {
                        if(firstTime)
                            loading_layout.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        if(firstTime)
                            loading_layout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        if(firstTime){
                            loading_layout.visibility = View.GONE
                        }
                        if (resources?.data?.data?.size ?: 0 > 0) {
                            empty.visibility = View.GONE
                            if(firstTime){
                                firstTime =false
                                transactionsAdapter = TransactionsAdapter(resources.data?.data, context)
                                rv_trans.adapter = transactionsAdapter
                                getAllTransactions(addressId, txCount)
                            }else{
                                transactionsAdapter.replaceAll(resources.data?.data)
                            }
                        } else {
                            empty.visibility = View.VISIBLE
                        }
                    }
                }
            })
    }

}