package com.example.listaformandos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AlbumFotos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private val imageList = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_upoads_fotos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        imageAdapter = ImageAdapter(imageList)
        recyclerView.adapter = imageAdapter

        // Carregar as imagens pela primeira vez
        fetchImagesFromFirebase()

        // Botão para abrir a Activity de upload
        val btnAlbum = findViewById<ImageButton>(R.id.addImage)
        btnAlbum.setOnClickListener {
            val intentAlbum = Intent(this, ImageListActivity::class.java)
            startActivity(intentAlbum)
        }
    }

    // Método chamado quando a Activity é retomada (ao retornar de outra Activity)
    override fun onResume() {
        super.onResume()
        // Atualizar a lista de imagens do Firebase
        updateImages()
    }

    // Função para atualizar as imagens
    private fun updateImages() {
        // Limpa a lista de imagens e notifica o adaptador
        imageList.clear()
        imageAdapter.notifyDataSetChanged()

        // Recarrega as imagens do Firebase
        fetchImagesFromFirebase()
    }

    private fun fetchImagesFromFirebase() {
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("images/")
        storageReference.listAll().addOnSuccessListener { listResult ->
            for (fileRef in listResult.items) {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    if (!imageList.contains(uri)) {
                        imageList.add(uri)  // Adiciona a URI da imagem à lista
                        imageAdapter.notifyDataSetChanged()  // Notifica o adaptador sobre a nova imagem
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Falha ao obter URL da imagem", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { exception ->
            Log.e("Firebase", "Erro ao listar as imagens", exception)
            Toast.makeText(this, "Erro ao buscar imagens", Toast.LENGTH_SHORT).show()
        }
    }
}
