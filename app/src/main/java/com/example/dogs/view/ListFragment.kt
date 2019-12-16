package com.example.dogs.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dogs.R
import com.example.dogs.adapter.DogListAdapter
import com.example.dogs.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogListAdapter = DogListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()
        dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogListAdapter
        }

        refreshLayout.setOnRefreshListener {
            dogsList.visibility = View.GONE
            errorMsg.visibility = View.GONE
            loaderView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.dogs.observe(this, Observer { dogs ->
            dogs?.let {
                dogsList.visibility = View.VISIBLE
                dogListAdapter.updateDogList(dogs)
            }
        })
        viewModel.dogLoadError.observe(this, Observer { isError ->
            isError?.let { errorMsg.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loaderView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    errorMsg.visibility = View.GONE
                    dogsList.visibility = View.GONE
                }
            }

        })
    }
}
