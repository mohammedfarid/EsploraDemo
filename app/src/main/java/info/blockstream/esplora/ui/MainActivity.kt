package info.blockstream.esplora.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import info.blockstream.esplora.R
import info.blockstream.esplora.ui.address.AddressFragment
import info.blockstream.esplora.utils.FragmentUtil

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentUtil.replaceFragment(
            this@MainActivity,
            AddressFragment(),
            false,
            AddressFragment.TAG
        )
    }
}