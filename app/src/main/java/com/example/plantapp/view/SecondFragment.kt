package com.example.plantapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.plantapp.databinding.FragmentSecondBinding
import com.example.plantapp.viewModel.PlantAppViewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    //ViewModel
    private val viewModel : PlantAppViewModel by activityViewModels()

    private var idPlanta : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            idPlanta = bundle.getInt("plantId")
            Log.d("Seleccion segFrament", idPlanta.toString())
        }

        idPlanta?.let { id ->
            viewModel.obtenerDetalleInternet(id)
        }

        viewModel.obtenerDetallePlantas().observe(viewLifecycleOwner, Observer {
            Log.d("Seleccion segFragment2", idPlanta.toString())

            var nombre = it.nombre
            var tipo = it.tipo

            //Cargar datos desde la selección
            Glide.with(binding.imgSegFragm).load(it.imagen).into(binding.imgSegFragm)
            binding.txtNombrePlant.text = "Nombre: ${it.nombre}"
            binding.txtTipoPlant.text = "Tipo: ${it.tipo}"
            binding.txtDescrPlant.text = "Descripción: ${it.descripcion}"

            //Enviar correo electrónico
            binding.btMail.setOnClickListener {

                //Inicializo intent
                val intent = Intent(Intent.ACTION_SEND)

                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"


                //Enviar correo a:
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("adminision@centrofuturo.cl"))

                //Asunto del correo
                intent.putExtra(Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este curso " + tipo

                )

                //Mensaje del correo
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n"+
                            " Vi el producto " +nombre+ " y me gustaría que me contactaran " +
                            "a este correo o al siguiente número  Quedo atento."

                )

                try {
                    startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}