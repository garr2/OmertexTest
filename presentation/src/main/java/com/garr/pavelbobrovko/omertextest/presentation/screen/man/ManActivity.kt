package com.garr.pavelbobrovko.omertextest.presentation.screen.man

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.garr.pavelbobrovko.omertextest.R
import com.garr.pavelbobrovko.omertextest.databinding.ActivityManBinding
import com.garr.pavelbobrovko.omertextest.presentation.base.BaseMvvmActivity

class ManActivity : BaseMvvmActivity<ManViewModel,ManRouter,ActivityManBinding>() {

    override fun prodiveViewModel(): ManViewModel {
       return ViewModelProviders.of(this).get(ManViewModel::class.java)
    }

    override fun provideRouter(): ManRouter {
        return ManRouter(this)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_man
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null){
            router.goToListFragment()
        }

    }

}
