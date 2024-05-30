package com.example.praktika17

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


var fig = arrayOf("Круг", "Квадрат", "Треугольник")
private lateinit var pref:SharedPreferences


    class perimeter : AppCompatActivity(), AdapterView.OnItemSelectedListener {
        private lateinit var spinner:Spinner
        private lateinit var editText1: EditText
        private lateinit var editText2: EditText
        private lateinit var editText3: EditText
        private lateinit var img:ImageView
        private lateinit var res:TextView
        private lateinit var pref:SharedPreferences


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perimeter)
            val spinner= findViewById<Spinner>(R.id.spinner)
            editText1 = findViewById(R.id.edittext1)
            editText2 = findViewById(R.id.edittext2)
            editText3 = findViewById(R.id.edittext3)
            img=findViewById(R.id.image)
            res=findViewById(R.id.result)
            var number:Int
            pref=getPreferences(MODE_PRIVATE)
            val selectedFigure:String
            selectedFigure=pref.getString("fig", "").toString()
            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.фигуры,
                android.R.layout.simple_spinner_item
            )
            number=0
            when (selectedFigure) {
                "Треугольник" -> {
                    number=2
                }

                "Квадрат" -> {
                    number=1
                }

                "Круг" -> {
                    number=0
                }
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(adapter)
            spinner.setPrompt("Фигуры");
            spinner.setSelection(number);
            spinner.setOnItemSelectedListener(this)




    }
       override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            var selectedFigure = parent.getItemAtPosition(position).toString()
            when (selectedFigure) {
                "Треугольник" -> {
                    editText1.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE)
                    editText3.setVisibility(View.VISIBLE)
                    img.setImageResource(R.drawable.tr)
                    pref=getPreferences(MODE_PRIVATE)
                    val ed = pref.edit()
                    ed.putString("fig", "Треугольник")
                    ed.apply()


                }

                "Квадрат" -> {
                    img.setImageResource(R.drawable.kv)
                    editText1.setVisibility(View.VISIBLE)
                    editText2.setVisibility(View.VISIBLE)
                    editText3.setVisibility(View.GONE)
                    pref=getPreferences(MODE_PRIVATE)
                    val ed = pref.edit()
                    ed.putString("fig", "Квадрат")
                    ed.apply()

                }

                "Круг" -> {
                    img.setImageResource(R.drawable.el)
                    editText1.setVisibility(View.VISIBLE)
                    editText2.setVisibility(View.GONE)
                    editText3.setVisibility(View.GONE)
                    pref=getPreferences(MODE_PRIVATE)
                    val ed = pref.edit()
                    ed.putString("fig", "Круг")
                    ed.apply()

                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }



        fun rs(view: View) {
            if(editText1.text.toString().isNotEmpty()&&editText2.text.toString().isNotEmpty()&&editText3.text.toString().isNotEmpty()){
                pref=getPreferences(MODE_PRIVATE)
                val number=pref.getString("fig", "").toString()
                val selectedFigure=number
                when (selectedFigure) {
                    "Треугольник" -> {
                        val side1: Double = editText1.getText().toString().toDouble()
                        val side2: Double = editText2.getText().toString().toDouble()
                        val side3: Double = editText3.getText().toString().toDouble()
                        val perimeter = side1 + side2 + side3
                        res.setText("Периметр треугольника: $perimeter")
                    }

                    "Квадрат" -> {
                        val length: Double = editText1.getText().toString().toDouble()
                        val width: Double = editText2.getText().toString().toDouble()
                        val rectanglePerimeter = 2 * (length + width)
                        res.setText("Периметр прямоугольника: $rectanglePerimeter")
                    }

                    "Круг" -> {
                        val radius: Double = editText1.getText().toString().toDouble()
                        val circlePerimeter = 2 * Math.PI * radius
                        res.setText("Периметр круга: $circlePerimeter")
                    }
                }
            }
            else{
                val toast = Toast.makeText(this, "Заполните пожалуйста поля", Toast.LENGTH_SHORT )
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
            }

        }


    }
