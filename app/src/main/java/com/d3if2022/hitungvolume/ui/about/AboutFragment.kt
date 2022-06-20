package com.d3if2022.hitungvolume.ui.about

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.d3if2022.hitungvolume.R
import com.d3if2022.hitungvolume.databinding.FragmentAboutBinding

import com.d3if2022.hitungvolume.model.ApiModel


class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        val viewModel: AboutViewModel by viewModels()
        viewModel.getUser().observe(viewLifecycleOwner, Observer<ApiModel> {
            binding.sipembuat.text = getString(R.string.sipembuat, it.name, it.company, it.location)
            Glide.with(binding.foto.context)
                .load(it.avatar_url)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.foto)
        })
        return binding.root
    }
}