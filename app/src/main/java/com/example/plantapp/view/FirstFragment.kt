package com.example.plantapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.PlantAppAdapter
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentFirstBinding
import com.example.plantapp.viewModel.PlantAppViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel : PlantAppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instancia del adaptador
        val adapter = PlantAppAdapter()
        binding.reciclerView.adapter = adapter
        binding.reciclerView.layoutManager = LinearLayoutManager(context)


        viewModel.obtenerListaPlantas().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Listado", it.toString())
                adapter.actualizar(it)
            }
        })


        //Método para seleccionar una planta
        adapter.seleccionarPlanta().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("Seleccion", it.toString())
            }
            val bundle = Bundle().apply {
                putInt("plantId", it.id)
            }

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })







    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}