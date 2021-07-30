package com.example.recycleviewaula

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewaula.databinding.ItemBinding

//Classe que servirá de ponte entre o código e o layout
//Ela vai receber uma View do tipo Item
class ItemAdapter(private val itemList: List<Item>, val listener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    //O ViewHolder representa um card, inner quer dizer que é uma classe interna
    inner class ItemViewHolder(private val itemBinding: ItemBinding): RecyclerView.ViewHolder(itemBinding.root/*Esta é a view*/){

        init {
            itemBinding.root.setOnClickListener {
                val pos = adapterPosition
                listener.onItemClick(pos)
            }

        }

        fun bind(item: Item){
            itemBinding.infoTextView.text = item.info
            itemBinding.titleTextView.text = item.title
            itemBinding.imageView.setImageResource(item.image)
        }

        //Esta função será executada toda vez que um item for clicado
        /*override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }*/
    }

    //Inflar o layout, criar uma instância do layout, e cria um viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(itemBinding)
    }

    //Setar o atributo do item pro ViewHold pro card
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
        Log.d("appPDM","${currentItem.title}")
    }

    //Retorna o tamanho da nossa lista
    override fun getItemCount() = itemList.size
}