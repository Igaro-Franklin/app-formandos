package com.example.listaformandos.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listaformandos.R
import com.example.listaformandos.modelo.Formando

class AdaptadorFormandos(private val context: Context, private val formandoList: MutableList<Formando>):
    RecyclerView.Adapter<AdaptadorFormandos.FormandoViewHolder>() {


    inner class FormandoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foto = itemView.findViewById<ImageView>(R.id.fotoFormando)
        val nome = itemView.findViewById<TextView>(R.id.textview)
        val descricao = itemView.findViewById<TextView>(R.id.descricao)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormandoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.item_formandos, parent, false)
        val holder = FormandoViewHolder(itemLista)
        return FormandoViewHolder(itemLista)
    }

    override fun getItemCount(): Int = formandoList.size

    override fun onBindViewHolder(holder: FormandoViewHolder, position: Int) {
        val formando = formandoList[position]
        holder.foto.setImageResource(formando.foto)
        holder.nome.text = formando.nome
        holder.descricao.text = formando.descricao
    }
}
