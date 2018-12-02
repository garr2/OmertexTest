package com.garr.pavelbobrovko.omertextest.presentation.base

import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast

abstract class BaseRouter<A : BaseActivity>(val activity: A) {

    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(e: Throwable){
        Toast.makeText(activity,"Error: $e", Toast.LENGTH_SHORT).show()
        Log.d("myLog","Router.showError: " + e.toString() + "  ${e.stackTrace}")
    }

    fun replaceFragment(fragmentManager: FragmentManager,
                        fragment: BaseFragment,
                        containerResId: Int, addToBackStack: Boolean = false) {

        var fragmentTransition = fragmentManager.beginTransaction()

        fragmentTransition.replace(containerResId, fragment,
                fragment::class.java.canonicalName)

        if(addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }

        fragmentTransition.commit()
    }
}