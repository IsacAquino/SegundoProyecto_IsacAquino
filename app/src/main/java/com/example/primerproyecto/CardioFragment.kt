package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class CardioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardio, container, false)
    }override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.boton_uno).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_burpeesFragment)
        }
        view.findViewById<Button>(R.id.boton_dos).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_junpingJackFragment)
        }
        view.findViewById<Button>(R.id.boton_tres).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_mountainClimbersFragment)
        }
        view.findViewById<Button>(R.id.boton_cuatro).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_skippingFragment)
        }
        view.findViewById<Button>(R.id.boton_cinco2).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_pullDown1Fragment)
        }
        view.findViewById<Button>(R.id.boton_seis).setOnClickListener {
            findNavController().navigate(R.id.action_cardioFragment_to_correrFragment)
        }
    }
}