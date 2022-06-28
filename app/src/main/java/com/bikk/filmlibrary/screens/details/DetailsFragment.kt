package com.bikk.filmlibrary.screens.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    private val viewModel: DetailsViewModel by viewModels()

}