package com.example.recycleviewaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewaula.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val itemList: ArrayList<Item> = generateList(100)
    private val adapter = ItemAdapter(itemList, this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("appPDM","${itemList.size}")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setContentView(R.layout.activity_main)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

    }

    //Função para gerar uma lista de elementos
    fun generateList(size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        //Para preencher a lista
        for(i in 0 until size){
            val item = Item(R.drawable.ic_baseline_adb_24, "Item $i", "Info")
            list.add(item)
        }
        return list
    }

    fun insertButton(view: View){
        val item = Item(R.drawable.ic_baseline_adb_24, "New Item", "Info")
        val position = (0..8).random()//O novo dado será colocado em uma posição do 1 ao 8
        itemList.add(position, item)//Adicionar o item na lista
        adapter.notifyItemInserted(position)//Notificar que o novo item foi adicionado em determinada posição
    }

    fun removeItem(view: View) {
        val position = (0..8).random()
        itemList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onItemClick(position: Int) {
        //Mensagem avisando que clicamos em um item
        Toast.makeText(this, "Clicked on ${itemList[position].title}", Toast.LENGTH_SHORT).show()
        //A descrição do item será alterada
        itemList[position].info = "You clicked on this item"
        adapter.notifyItemChanged(position)//Avisar que o item foi alterado
    }

}



