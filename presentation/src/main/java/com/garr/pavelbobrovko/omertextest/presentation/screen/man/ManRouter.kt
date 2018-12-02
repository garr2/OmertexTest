package com.garr.pavelbobrovko.omertextest.presentation.screen.man

import android.view.View
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseRouter
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.detalis.ManDetalisFragment
import com.garr.pavelbobrovko.omertextest.presentation.screen.man.list.ManListFragment

class ManRouter(activity: ManActivity): BaseRouter<ManActivity>(activity) {

    fun goToDetalisFragment(id: String){
        val containerDetails = activity.findViewById<View>(R.id.flFragmentDetalis)

        if (containerDetails != null){
            replaceFragment(activity.supportFragmentManager,ManDetalisFragment.getInstance(id)
                ,R.id.flFragmentDetalis,false)
        }else{
            replaceFragment(activity.supportFragmentManager,ManDetalisFragment.getInstance(id)
                ,R.id.flFragmentList,true)
        }
    }

    fun goToListFragment(){
        replaceFragment(activity.supportFragmentManager,ManListFragment.getInstance(),R.id.flFragmentList)
    }
}