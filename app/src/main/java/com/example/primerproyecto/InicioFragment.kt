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
import com.example.primerproyecto.entities.Categorias
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InicioFragment : Fragment() {

    private lateinit var categoriasList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseTitle = view.findViewById<TextView>(R.id.database_title)

        categoriasList = view.findViewById(R.id.categorias_list)
        categoriasList.layoutManager = LinearLayoutManager(requireContext())

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(CategoriasListViewModel::class.java)

        val adapter = CategoriaListAdapter { categoria ->
            when (categoria.nombre) {
                "Brazos" -> findNavController().navigate(R.id.action_inicioFragment_to_brazosFragment)
                "Espalda" -> findNavController().navigate(R.id.action_inicioFragment_to_espaldaFragment)
                "Cardio" -> findNavController().navigate(R.id.action_inicioFragment_to_cardioFragment)
                "Abdominales" -> findNavController().navigate(R.id.action_inicioFragment_to_abdominalesFragment)
            }
        }
        categoriasList.adapter = adapter

        viewModel.categorias.observe(viewLifecycleOwner) { categorias ->
            if (!categorias.isNullOrEmpty()) {
                adapter.submitList(categorias)
            }
        }

        val dbName = DatabaseProvider.getInstance(requireContext()).openHelper.databaseName
        databaseTitle.text = dbName ?: "Nombre de la Base de Datos"

        viewModel.load()
    }

    class CategoriasListViewModel(application: android.app.Application) : AndroidViewModel(application) {
        private val _categorias: MutableLiveData<List<Categorias>> = MutableLiveData()
        val categorias: LiveData<List<Categorias>> = _categorias

        fun load() {
            val db = DatabaseProvider.getInstance(getApplication())
            CoroutineScope(Dispatchers.IO).launch {
                val categoriasDaos = db.categoriasDaos()
                var categorias = categoriasDaos.obtenerCategorias()

                if (categorias.isEmpty()) {
                    categoriasDaos.insertarMuchos(
                        Categorias(id = 0, nombre = "Brazos"),
                        Categorias(id = 0, nombre = "Espalda"),
                        Categorias(id = 0, nombre = "Cardio"),
                        Categorias(id = 0, nombre = "Abdominales")
                    )
                    categorias = categoriasDaos.obtenerCategorias()
                }

                withContext(Dispatchers.Main) {
                    _categorias.postValue(categorias)
                }
            }
        }
    }

    class CategoriaListAdapter(
        private val onItemClick: (Categorias) -> Unit
    ) : ListAdapter<Categorias, CategoriaListAdapter.WordViewHolder>(WordsComparator()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.categorias_list_item, parent, false)
            return WordViewHolder(view, onItemClick)
        }

        override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
            val current = getItem(position)
            holder.bind(current)
        }

        class WordViewHolder(itemView: View, private val onItemClick: (Categorias) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

            private val categoriaNameTextView: TextView =
                itemView.findViewById(R.id.categorias_name_text_view)

            fun bind(categorias: Categorias) {
                categoriaNameTextView.text = categorias.nombre
                categoriaNameTextView.setOnClickListener {
                    onItemClick(categorias)
                }
            }

        }

        class WordsComparator : DiffUtil.ItemCallback<Categorias>() {
            override fun areItemsTheSame(oldItem: Categorias, newItem: Categorias): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Categorias, newItem: Categorias): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
