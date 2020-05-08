package fr.kotlin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {


    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onEqual(view: View){
        if(lastNumeric){
            // on récupère l'input
            var value = tvInput.text.toString()
            var prefix = ""

            try {
                if(value.startsWith("-")){
                    prefix = "-"
                    value = value.substring(1)
                }
                if(value.contains("-")){
                    val splitedValue = value.split("-")
                    var diminuende = splitedValue[0]
                    val diminutor = splitedValue[1]

                    if(!prefix.isEmpty()){
                        diminuende = prefix + diminuende
                    }

                    val difference = removeZero((diminuende.toDouble() - diminutor.toDouble()).toString())
                    tvInput.text = difference
                }
                if(value.contains("+")){
                    val splitedValue = value.split("+")
                    val terme1 = splitedValue[0]
                    val terme2 = splitedValue[1]

                    val somme = removeZero((terme1.toDouble() + terme2.toDouble()).toString())
                    tvInput.text = somme
                }
                if(value.contains("/")){
                    val splitedValue = value.split("/")
                    val num1 = splitedValue[0]
                    val num2 = splitedValue[1]

                    val quotient = removeZero((num1.toDouble() / num2.toDouble()).toString())
                    tvInput.text = quotient
                }
                if(value.contains("*")){
                    val splitedValue = value.split("*")
                    val num1 = splitedValue[0]
                    val num2 = splitedValue[1]

                    val result = removeZero((num1.toDouble() * num2.toDouble()).toString())
                    tvInput.text = result
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun removeZero(result: String): String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length-2)
            //99.0

        return value
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
