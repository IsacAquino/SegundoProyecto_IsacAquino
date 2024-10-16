package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class InicioFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.brazos_button).setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_brazosFragment)
        }
        view.findViewById<Button>(R.id.espaldas_button).setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_espaldaFragment)
        }
        view.findViewById<Button>(R.id.cardio_button).setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_cardioFragment)
        }
        view.findViewById<Button>(R.id.abdominales_button).setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_abdominalesFragment)
        }




    }
}
