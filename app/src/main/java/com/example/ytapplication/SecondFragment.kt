package com.example.ytapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.navArgs
import com.example.ytapplication.databinding.FragmentSecondBinding

class SecondFragment: Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.tvSecondFragment.text = arguments?.getString("key")
        binding.tvSecondFragment.text = args.sfInput.secondFragmentText

        binding.btnNavigate.setOnClickListener {
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).onSecondFragmentButtonClicked()
            }
        }
    }

}
