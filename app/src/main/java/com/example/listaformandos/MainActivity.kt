package com.example.listaformandos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnProxTela = findViewById<ImageButton>(R.id.proxTela)
        btnProxTela.setOnClickListener {
            val intentProxTela = Intent(this, ListaFormandos::class.java)
            startActivity(intentProxTela)
        }

        val btnAlbum = findViewById<ImageButton>(R.id.btnAlbum)
        btnAlbum.setOnClickListener {
            val intentAlbum = Intent(this, AlbumFotos::class.java)
            startActivity(intentAlbum)
        }

    }
}