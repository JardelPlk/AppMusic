package com.example.recycleviewaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewaula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var itemList: ArrayList<Musica> = generateList(0)
    private val adapter = ItemAdapter(itemList, this)
    private var indexMusica: Int = -1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("appPDM","${itemList.size}")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

    }

    //Função para gerar uma lista de elementos
    fun generateList(size: Int): ArrayList<Musica>{
        val list = ArrayList<Musica>()
        //Para preencher a lista
        for(i in 0 until size){
            val item = Musica(R.drawable.ic_baseline_queue_music_24, "Música $i", "Sem nome")
            list.add(item)
        }
        return list
    }

    fun insertButton(view: View){

        val musica = binding.musicaEditText.text.toString()
        val artista = binding.artistaEditText.text.toString()

        val item = Musica(R.drawable.ic_baseline_queue_music_24, musica, artista)
        //val position = (0..8).random()//O novo dado será colocado em uma posição do 1 ao 8
        itemList.add(0, item)//Adicionar o item na lista
        adapter.notifyItemInserted(0)//Notificar que o novo item foi adicionado em determinada posição
    }


    fun removeItem(view: View) {
        if(indexMusica != -1){
            itemList.removeAt(indexMusica)
            adapter.notifyItemRemoved(indexMusica)
            Toast.makeText(this, "Música removida", Toast.LENGTH_LONG).show()
            indexMusica = -1
        }else{
            Toast.makeText(this, "Deseja remover alguma música?", Toast.LENGTH_SHORT).show()
        }
        /*val position = (0..8).random()
        itemList.removeAt(position)
        adapter.notifyItemRemoved(position)*/
    }

    override fun onItemClick(position: Int) {
        val item: Musica = itemList[position]

        val cliqueMusica: EditText = binding.musicaEditText
        cliqueMusica.setText(item.title)
        val cliqueArtista: EditText = binding.artistaEditText
        cliqueArtista.setText(item.artista)

        indexMusica = position
        adapter.notifyItemChanged(position)

        /*//Mensagem avisando que clicamos em um item
        Toast.makeText(this, "Clicked on ${itemList[position].musica}", Toast.LENGTH_SHORT).show()
        //A descrição do item será alterada
        itemList[position].artista = "You clicked on this item"
        adapter.notifyItemChanged(position)//Avisar que o item foi alterado
        */
    }

    fun editItem(view: View) {
        if(indexMusica != -1){
            val musica = binding.musicaEditText.text.toString()
            val artista = binding.artistaEditText.text.toString()

            val item = Musica(R.drawable.ic_baseline_queue_music_24, musica, artista)
            itemList[indexMusica].title = item.title
            itemList[indexMusica].artista = item.artista
            adapter.notifyItemChanged(indexMusica)

            Toast.makeText(this, "Música alterada", Toast.LENGTH_LONG).show()
            indexMusica = -1
        }else{
            Toast.makeText(this, "Deseja alterar alguma música?", Toast.LENGTH_SHORT).show()
        }
    }


}



