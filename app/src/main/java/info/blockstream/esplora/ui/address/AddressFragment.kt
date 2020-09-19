package info.blockstream.esplora.ui.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import info.blockstream.esplora.R
import info.blockstream.esplora.ui.MainActivity
import info.blockstream.esplora.ui.addressdetails.TransactionsFragment
import info.blockstream.esplora.utils.FragmentUtil
import info.blockstream.esplora.utils.Status
import kotlinx.android.synthetic.main.fragment_address.*


@AndroidEntryPoint
class AddressFragment : Fragment() {

    val addressViewModel: AddressViewModel by viewModels()
    lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.address)

        addressViewModel.getAddress(this@AddressFragment.context).observe(viewLifecycleOwner,
            { resources ->
                when (resources.status) {
                    Status.LOADING -> {
                        loading_layout.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        loading_layout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        loading_layout.visibility = View.GONE

                        addressAdapter = AddressAdapter(results = resources?.data?.results)
                        { address ->
                            FragmentUtil.replaceFragment(
                                activity as MainActivity,
                                TransactionsFragment.newInstants(address),
                                true,
                                TransactionsFragment.TAG
                            )
                        }
                        rv_address.adapter = addressAdapter
                    }
                }
            })
    }

    companion object {
        val TAG = AddressFragment::class.java.simpleName
    }
}