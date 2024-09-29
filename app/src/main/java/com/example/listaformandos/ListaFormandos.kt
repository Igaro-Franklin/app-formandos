package com.example.listaformandos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listaformandos.adaptador.AdaptadorFormandos
import com.example.listaformandos.modelo.Formando

class ListaFormandos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_formandos)

        val recyclerFormandos = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerFormandos.layoutManager = LinearLayoutManager(this)

        recyclerFormandos.setHasFixedSize(true)

        val listaFormandos: MutableList<Formando> = mutableListOf()
        val adaptadorFormandos = AdaptadorFormandos(this, listaFormandos)
        recyclerFormandos.adapter = adaptadorFormandos

        val abrahan = Formando(
            R.drawable.abrahan,
            "Abrahan",
            "\"Deus é bom e fiel\""
        )
        listaFormandos.add(abrahan)

        val alexandre = Formando(
            R.drawable.alexandre,
            "Alexandre",
            "\"Na escala da vida existe um lema, nunca desistir\""
        )
        listaFormandos.add(alexandre)

        val bernardo = Formando(
            R.drawable.bernardo,
            "Bernardo",
            "\"A única maneira de fazer um ótimo trabalho é amar o que você faz\""
        )
        listaFormandos.add(bernardo)

        val douglas = Formando(
            R.drawable.douglas,
            "Douglas",
            "\"Liberdade é não ter medo\""
        )
        listaFormandos.add(douglas)

        val franklin = Formando(
            R.drawable.franklin,
            "Franklin",
            "\"É necessário sempre acreditar que o sonho é possível\""
        )
        listaFormandos.add(franklin)

        val gilliard = Formando(
            R.drawable.gilliard,
            "Gilliard",
            "\"Objetivo é um sonho com prazo de entrega\""
        )
        listaFormandos.add(gilliard)

        val grazielle = Formando(
            R.drawable.grazielle,
            "Grazielle",
            "\"A cada novo dia, uma nova oportunidade de ser melhor do que ontem\""
        )
        listaFormandos.add(grazielle)

        val jessica = Formando(
            R.drawable.jessica,
            "Jéssica",
            "\"Toda conquista começa com a decisão de tentar\""
        )
        listaFormandos.add(jessica)

        val miqueas = Formando(
            R.drawable.miqueas,
            "Miqueas",
            "\"Eu nunca perco. Ou eu ganho ou eu aprendo\""
        )
        listaFormandos.add(miqueas)

        val paulo = Formando(
            R.drawable.paulo,
            "Paulo",
            "\"É importante agradecer pelo hoje sem nunca desistir do amanhã!\""
        )
        listaFormandos.add(paulo)

        val roberto = Formando(
            R.drawable.roberto,
            "Roberto",
            "\"Estabilidade não existe!\""
        )
        listaFormandos.add(roberto)

        val rodrigo = Formando(
            R.drawable.rodrigo,
            "Rodrigo",
            "\"Será o homem um erro de Deus, ou Deus um erro dos homens?\""
        )
        listaFormandos.add(rodrigo)

        val tiago = Formando(
            R.drawable.tiago,
            "Tiago",
            "\"O homem não teria alcançado o possível se não tivesse tentado o impossível\""
        )
        listaFormandos.add(tiago)

        val tobias = Formando(
            R.drawable.tobias,
            "Tobias",
            "\"Grato por todo plano do Senhor na minha vida!!!\""
        )
        listaFormandos.add(tobias)

        val wirley = Formando(
            R.drawable.wirley,
            "Wirley",
            "\"Nada é impossível, tudo é possível naquele que acredita\""
        )
        listaFormandos.add(wirley)

        val victor = Formando(
            R.drawable.victor,
            "Victor",
            "\"Se você tropeçou, é porque estava seguindo em frente\""
        )
        listaFormandos.add(victor)
    }
}