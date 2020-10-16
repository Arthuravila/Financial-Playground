package com.app.financialplayground.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.financialplayground.R
import com.app.financialplayground.core.base.BaseFragment
import com.app.financialplayground.data.ViewState
import com.app.financialplayground.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.fetchFinances()
    }

    override fun subscribeUi() {
        homeViewModel.viewStateLiveData.observe(this) { result ->
            when (result) {
                is ViewState.Success -> {
                    val res = result
                    val art = ""
                }
                is ViewState.Error -> {

                }
                is ViewState.Loading -> {

                }
            }
        }
    }
}
