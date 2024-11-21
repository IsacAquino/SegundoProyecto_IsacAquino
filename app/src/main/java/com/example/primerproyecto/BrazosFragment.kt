package com.example.primerproyecto

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.db.DatabaseProvider
import com.example.primerproyecto.entities.Ejercicios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BrazosFragment : Fragment() {

    private lateinit var ejerciciosList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brazos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el RecyclerView
        ejerciciosList = view.findViewById(R.id.ejercicios_list)
        ejerciciosList.layoutManager = LinearLayoutManager(requireContext())

        // Configura el ViewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(EjerciciosListViewModel::class.java)

        val adapter = EjerciciosAdapter { ejercicio ->
            // Configura la navegación a los fragmentos correspondientes
            when (ejercicio.nombre) {
                "Curl de Bíceps" -> findNavController().navigate(R.id.action_brazosFragment_to_curlDeBicepsFragment)
                "Tríceps en Polea" -> findNavController().navigate(R.id.action_brazosFragment_to_tricepsPoleaFragment)
                "Press Militar" -> findNavController().navigate(R.id.action_brazosFragment_to_pressMilitarFragment)
                "Fondos en Paralelas" -> findNavController().navigate(R.id.action_brazosFragment_to_fondosParalelasFragment)
                "Curl Martillo" -> findNavController().navigate(R.id.action_brazosFragment_to_curlMartilloFragment)
                "Press con Mancuernas" -> findNavController().navigate(R.id.action_brazosFragment_to_pressMancuernasFragment)
            }
        }
        ejerciciosList.adapter = adapter

        // Observa los cambios en los ejercicios
        viewModel.ejercicios.observe(viewLifecycleOwner) { ejercicios ->
            if (!ejercicios.isNullOrEmpty()) {
                adapter.submitList(ejercicios)
            }
        }

        // Cargar los datos
        viewModel.load()
    }


    // ViewModel para manejar los ejercicios
    class EjerciciosListViewModel(application: android.app.Application) : AndroidViewModel(application) {
        private val _ejercicios: MutableLiveData<List<Ejercicios>> = MutableLiveData()
        val ejercicios: LiveData<List<Ejercicios>> = _ejercicios

        fun load() {
            val db = DatabaseProvider.getInstance(getApplication())
            CoroutineScope(Dispatchers.IO).launch {
                val ejerciciosDao = db.ejerciciosDaos()
                var ejercicios = ejerciciosDao.getEjerciciosPorCategoria(1) // ID para "Brazos"

                // Inserta ejercicios si no hay datos
                if (ejercicios.isEmpty()) {
                    ejerciciosDao.insertarMuchos(
                        Ejercicios(0, "Curl de Bíceps", "Levanta las mancuernas en un movimiento controlado.", 1),
                        Ejercicios(0, "Tríceps en Polea", "Empuja la polea hacia abajo hasta extender completamente los brazos.", 1),
                        Ejercicios(0, "Press Militar", "Levanta la barra por encima de tu cabeza con los codos extendidos.", 1),
                        Ejercicios(0, "Fondos en Paralelas", "Baja el cuerpo doblando los codos y sube extendiéndolos.", 1),
                        Ejercicios(0, "Curl Martillo", "Levanta las mancuernas con un agarre neutro.", 1),
                        Ejercicios(0, "Press con Mancuernas", "Empuja las mancuernas hacia arriba desde el pecho.", 1)
                    )
                    ejercicios = ejerciciosDao.getEjerciciosPorCategoria(1)
                }

                withContext(Dispatchers.Main) {
                    _ejercicios.postValue(ejercicios)
                }
            }
        }
    }

    // Adaptador para manejar los ejercicios
    class EjerciciosAdapter(
        private val onItemClick: (Ejercicios) -> Unit
    ) : ListAdapter<Ejercicios, EjerciciosAdapter.EjerciciosViewHolder>(EjerciciosComparator()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjerciciosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ejercicios_list_item, parent, false)
            return EjerciciosViewHolder(view, onItemClick)
        }

        override fun onBindViewHolder(holder: EjerciciosViewHolder, position: Int) {
            val current = getItem(position)
            holder.bind(current)
        }

        class EjerciciosViewHolder(
            itemView: View,
            private val onItemClick: (Ejercicios) -> Unit
        ) : RecyclerView.ViewHolder(itemView) {

            private val ejercicioNameTextView: TextView =
                itemView.findViewById(R.id.ejercicio_name_text_view)

            fun bind(ejercicio: Ejercicios) {
                ejercicioNameTextView.text = ejercicio.nombre
                ejercicioNameTextView.setOnClickListener { onItemClick(ejercicio) }
            }
        }

        class EjerciciosComparator : DiffUtil.ItemCallback<Ejercicios>() {
            override fun areItemsTheSame(oldItem: Ejercicios, newItem: Ejercicios): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ejercicios, newItem: Ejercicios): Boolean {
                return oldItem == newItem
            }
        }
    }



}
