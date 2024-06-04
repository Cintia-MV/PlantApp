package com.example.plantapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.databinding.PlantListBinding
import com.example.plantapp.model.local.entities.ListaPlantasLocal

class PlantAppAdapter : RecyclerView.Adapter<PlantAppAdapter.ListaPlantasVH>(){

    //Referencias para el adapter
    private var listaPlantas = listOf<ListaPlantasLocal>()
    private val plantaSeleccionada = MutableLiveData<ListaPlantasLocal>()

    //Función para actualizar el adapter
    fun actualizar(lista : List<ListaPlantasLocal>){
        listaPlantas = lista
        notifyDataSetChanged()
    }

    // Función para seleccionar una planta
    fun seleccionarPlanta(): LiveData<ListaPlantasLocal> = plantaSeleccionada


    //Funciones de view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPlantasVH {
        return ListaPlantasVH(PlantListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listaPlantas.size

    override fun onBindViewHolder(holder: ListaPlantasVH, position: Int) {
        val planta = listaPlantas[position]
        holder.bin(planta)
    }


    //Clase interna
    inner class ListaPlantasVH(private val binding: PlantListBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{

                fun bin(planta: ListaPlantasLocal){
                    Glide.with(binding.imgPlanta).load(planta.imagen).centerCrop().override(500, 500).into(binding.imgPlanta)
                    binding.textNombre.text = "Nombre: ${planta.nombre}"
                    binding.textTipo.text = "Tipo: ${planta.tipo}"
                    binding.textDescripcion.text = "Descipción: ${planta.descripcion}"

                    itemView.setOnClickListener(this)
                }




        override fun onClick(v: View?) {
           plantaSeleccionada.value = listaPlantas[adapterPosition]
        }

    }



}