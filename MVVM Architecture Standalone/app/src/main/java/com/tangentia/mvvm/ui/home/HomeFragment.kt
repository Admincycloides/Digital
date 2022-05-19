package com.tangentia.mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tangentia.mvvm.ApplicationClass
import com.tangentia.mvvm.ApplicationClass.Companion.appContext
import com.tangentia.mvvm.R
import com.tangentia.mvvm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            requireContext().applicationContext as ApplicationClass
        )
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //context
        val application = requireNotNull(this.activity).application

        val view = binding.root

        setText()
        return view
    }

    private fun setText() {
        binding.myTextview.text = appContext.getString(R.string.my_text)
    }
}