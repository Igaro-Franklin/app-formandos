package com.example.listaformandos

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class ImageListActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnAbrirGaleria: Button
    private lateinit var btnTirarFoto: Button
    private lateinit var btnEnviarFoto: Button
    private var imageUri: Uri? = null
    private var cameraImageUri: Uri? = null

    // Código para a galeria e câmera
    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
        private const val CAMERA_REQUEST_CODE = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upload_fotos)

        imageView = findViewById(R.id.imageView)
        btnAbrirGaleria = findViewById(R.id.btnAbrirGaleria)
        btnTirarFoto = findViewById(R.id.btnTirarFoto)
        btnEnviarFoto = findViewById(R.id.btnEnviarFoto)

        // Abrir a galeria
        btnAbrirGaleria.setOnClickListener {
            openGallery()
        }

        // Tirar foto com a câmera
        btnTirarFoto.setOnClickListener {
            openCamera()
        }

        // Enviar a foto para o Firebase
        btnEnviarFoto.setOnClickListener {
            if (imageUri != null || cameraImageUri != null) {
                uploadImageToFirebase()
            } else {
                Toast.makeText(this, "Selecione ou tire uma imagem primeiro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Função para abrir a galeria
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    // Função para abrir a câmera
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    // Verifica os resultados da galeria e da câmera
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Verificar se a imagem veio da galeria
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }

        // Verificar se a imagem veio da câmera
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)

            // Salvar a imagem em um arquivo temporário e obter o URI
            val tempUri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, photo, "Captured Image", null))
            cameraImageUri = tempUri
        }
    }

    // Função para fazer o upload da imagem para o Firebase
    private fun uploadImageToFirebase() {
        val storageReference = FirebaseStorage.getInstance().reference
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val fileReference = storageReference.child("images/$fileName")

        val uriToUpload = imageUri ?: cameraImageUri

        uriToUpload?.let {
            fileReference.putFile(it)
                .addOnSuccessListener {
                    Toast.makeText(this, "Imagem enviada com sucesso!", Toast.LENGTH_SHORT).show()

                    // Voltar para a tela do RecyclerView após o upload
                    val intent = Intent(this, AlbumFotos::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao enviar a imagem", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
