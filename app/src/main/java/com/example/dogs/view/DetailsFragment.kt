package com.example.dogs.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavGraph
import androidx.navigation.Navigation

import com.example.dogs.R
import com.example.dogs.utils.getProgressDrawable
import com.example.dogs.utils.loadImage
import com.example.dogs.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    private var dogUuid = 0
    private lateinit var viewModel : DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            dogUuid = DetailsFragmentArgs.fromBundle(it).dogUuid
        }
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.dogLiveData.observe(this, Observer{
            dog-> dog?.let {
            dogName.text = dog.dogBreed
            dogPurpose.text = dog.breadFor
            dogTemperament.text = dog.temperament
            dogLifespan.text = dog.lifeSpan
            context?.let {dogImg.loadImage(dog.imgUrl, getProgressDrawable(it))}
        }
        })
    }

}
