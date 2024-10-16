package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class AbdominalesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abdominales, container, false)
    }override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.boton_uno).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_crunchesFragment)
        }
        view.findViewById<Button>(R.id.boton_dos).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_plankFragment)
        }
        view.findViewById<Button>(R.id.boton_tres).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_bicyclesCrunchesFragment)
        }
        view.findViewById<Button>(R.id.boton_cuatro).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_legRaisesFragment)
        }
        view.findViewById<Button>(R.id.boton_cinco2).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_russianTwistsFragment)
        }
        view.findViewById<Button>(R.id.boton_seis).setOnClickListener {
            findNavController().navigate(R.id.action_abdominalesFragment_to_mountainClimbers1Fragment)
        }
    }
}