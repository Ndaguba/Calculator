package eu.tutrorials.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    // lets create a variable for the input
    private var tvInput: TextView? = null

    // boolean variables for decimal points
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    // you get the button that was clicked by using view: View
    fun onDigit(view: View) {
        // append means to add something to an exsisting string
        // remember ? - nullable
        // tvInput?.append("1") will add 1 to the exsisting string
        tvInput?.append((view as Button).text) // .text retrieves the text on the button
        lastNumeric = true
        lastDot = false
    }

    // always remember that when using button to retrieve something we us view View as function parameter
    fun onClear(view: View) {
        // when the clear button is clicked
        tvInput?.text = ""
    }

    // we need to be able to ensure that if there is a decimal point, another wont be added
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            //if the last input was a number
            lastNumeric = false
            // if a dot was inputed before
            lastDot = true
            //flags
        }
    }
    fun onOperator(view: View) {
        //check if the textview input is empty
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                //append whatevet the button is
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    // function executed once the equal button is pressed
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            // to prevent our application from crashing
            try{
                if(tvValue.startsWith("-")){
                    prefix ="-"
                    tvValue = tvValue.substring(1)

                }
                // we want to split the code at " "
                //1
               if(tvValue.contains("-")){
                   val splitValue = tvValue.split("-")

                   var one = splitValue[0] // 99
                   var two = splitValue[1] // 1

                   if(prefix.isNotEmpty()){
                       one = prefix + one
                   }
                   //   var result =

                   tvInput?.text = (one.toDouble() - two.toDouble()).toString()
               }
               //2 code for addition
               else if(tvValue.contains("+")){
                   val splitValue = tvValue.split("+")

                   var one = splitValue[0] // 99
                   var two = splitValue[1] // 1

                   if(prefix.isNotEmpty()){
                       one = prefix + one
                   }
                   //   var result =

                   tvInput?.text = (one.toDouble() + two.toDouble()).toString()

               }
               //3 operation for division
               else if(tvValue.contains("/")){
                   val splitValue = tvValue.split("/")

                   var one = splitValue[0] // 99
                   var two = splitValue[1] // 1

                   if(prefix.isNotEmpty()){
                       one = prefix + one
                   }
                   //   var result =

                   tvInput?.text = (one.toDouble() / two.toDouble()).toString()
               }
                // multiplication
                else if(tvValue.contains("*")){
                   val splitValue = tvValue.split("*")

                   var one = splitValue[0] // 99
                   var two = splitValue[1] // 1

                   if(prefix.isNotEmpty()){
                       one = prefix + one
                   }
                   //   var result =

                   tvInput?.text = ((one.toDouble() *  two.toDouble()).toString())

               }

            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }

    }

   // if it contains these operators
    private fun isOperatorAdded(value: String): Boolean {
    return if(value.startsWith("")){
        false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
    }
