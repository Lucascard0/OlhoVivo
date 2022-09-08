package com.example.olhovivoaikoproj.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olhovivoaikoproj.R
import com.example.olhovivoaikoproj.databinding.ActivityMainBinding
import com.example.olhovivoaikoproj.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private var textoBusca: String = ""
    private lateinit var textScreen: TextView
    private lateinit var textDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//    private fun initRecyclerView(){
//        binding.rvLinhas.layoutManager = LinearLayoutManager(this)
//        binding.rvLinhas.setHasFixedSize(true)
//        binding.rvLinhas.adapter = Adapter(getList())          //Parte que ta dando erro
//    }

    override fun onResume() {
        super.onResume()
        getSearch()
        mainViewModel.getAuthenticateToken(
            onSucess =  {
                binding.textScreen.text = it.toString()
                getLinhas()
            },
            onFailure =  {
                binding.textScreen.text = it.message?.toString()
            }
        )
    }

    private fun getLinhas() {

        mainViewModel.getLinhas(
            termosBuscas = textoBusca,
            onSucess = {
                //initRecyclerView()  //Inicializar a recyclerView
                binding.textDescription.text = it.toString()
            },
            onFailure =  {
                binding.textDescription.text = it.message?.toString()
            }
        )
    }

    private fun getSearch(){
        binding.buttonBuscar.setOnClickListener {
            textoBusca = binding.searchMain.text.toString()
            //Toast.makeText(this, "O texto foi: $textoBusca", Toast.LENGTH_LONG).show()
            getLinhas()
            binding.searchMain.text.clear()

        }
    }

    private fun getList() = listOf(
        getLinhas()
    )

}
