package info.blockstream.esplora.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import info.blockstream.esplora.R

object FragmentUtil {

    fun replaceFragment(
        activity: AppCompatActivity,
        fragment: Fragment,
        addToBackstack: Boolean,
        TAG: String
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment, fragment, TAG)

        if (addToBackstack) {
            transaction.addToBackStack(TAG)
        }

        transaction.commit()
    }

}