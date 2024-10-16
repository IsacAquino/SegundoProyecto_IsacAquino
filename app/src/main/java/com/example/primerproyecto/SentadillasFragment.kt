package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primerproyecto.databinding.FragmentSentadillasBinding

class SentadillasFragment : Fragment() {

    private lateinit var binding: FragmentSentadillasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using DataBinding
        binding = FragmentSentadillasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Establecer datos en el layout usando DataBinding
        binding.ejercicioNombre.text = "Sentadillas"
        binding.ejercicioDescripcion.text = "Las sentadillas son un excelente ejercicio para fortalecer los músculos de las piernas y los glúteos."
        binding.ejercicioInstrucciones.text = "Párate derecho con los pies separados al ancho de los hombros, baja lentamente como si fueras a sentarte y luego sube."
    }

}