package com.example.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG: String = "로그"
    }

    lateinit var myNumberViewModel: MyNumberViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        // setContentView(R.layout.activity_main)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextview.text = it.toString()
        })

        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val userInput = binding.userinputEdittext.text.toString().toInt()

        when(view) {
            binding.btnPlus ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            binding.btnMinus ->
                myNumberViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }

}