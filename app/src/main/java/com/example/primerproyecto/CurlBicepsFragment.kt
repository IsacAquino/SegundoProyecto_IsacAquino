package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.primerproyecto.databinding.FragmentCurlBicepsBinding


// CurlDeBicepsFragment.kt
class CurlBicepsFragment : Fragment() {

    private lateinit var binding: FragmentCurlBicepsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_curl_biceps, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set data in the layout using DataBinding
        binding.ejercicioNombre.text = "Curl de Bíceps"
        binding.ejercicioDescripcion.text = "El curl de bíceps es un ejercicio básico para trabajar los músculos del brazo."
        binding.ejercicioInstrucciones.text = "1. Sostén una mancuerna en cada mano. \n2. Flexiona los codos para elevar las mancuernas. \n3. Baja lentamente las pesas."
    }
}
