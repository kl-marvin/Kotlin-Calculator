package fr.kotlin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onDigit(view : View){
       // Toast.makeText(this, "Btn clicked", Toast.LENGTH_LONG).show()

        // on récupère le texte du button et on l'ajoute dans l'input
        tvInput.append((view as Button).text)
        lastNumeric = true

    }

    fun onDecimalPoint(view: View){
        // we check if the last btn was numeric and not a dot
        if(lastNumeric && !lastDot){
            // if so we add a . and put lastDot to true
            tvInput.append(".")

            lastNumeric = false
            lastDot = true
        }
    }


    fun clear(view: View){
        tvInput.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onOperator(view: View){
        // si la value est un numérique et n'est pas un opérateur
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            // ajoute l'opérateur
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun isOperatorAdded(value:String) : Boolean{
        // won't block when calculating a value that is < 0
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
            value.contains("*") ||
            value.contains("+") ||
            value.contains("-")
        }
    }


}
