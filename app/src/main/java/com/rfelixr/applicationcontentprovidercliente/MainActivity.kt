package com.rfelixr.applicationcontentprovidercliente

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rfelixr.applicationcontentprovidercliente.adapter.ClientAdapter
import com.rfelixr.applicationcontentprovidercliente.adapter.ClientViewHolder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var notesRecyclerView: RecyclerView
    lateinit var noteRefleshButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRefleshButton = findViewById(R.id.client_button_refresh)
        notesRecyclerView = findViewById(R.id.client_list)

        getContentProvider()

        noteRefleshButton.setOnClickListener { getContentProvider() }
    }

    private fun getContentProvider(){
        try {
            val url = "content://com.rfelixr.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor:Cursor? = contentResolver.query(data,null,null,null,"title")
            notesRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}