package com.example.primerproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class PiernasFragment : Fragment() {

    class PiernasFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_piernas, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Listener para el botón Sentadillas
            view.findViewById<Button>(R.id.boton_uno).setOnClickListener {
                findNavController().navigate(R.id.action_piernasFragment_to_sentadillasFragment)
            }
        }
    }


}
