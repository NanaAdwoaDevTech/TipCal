package com.example.tipcal

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : Activity() {

    private lateinit var billInput: EditText
    private lateinit var tipLabel: TextView
    private lateinit var tipBar: SeekBar
    private lateinit var splitLabel: TextView
    private lateinit var splitBar: SeekBar
    private lateinit var tipAmount: TextView
    private lateinit var total: TextView
    private lateinit var perPerson: TextView
    private val money = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pad = (20 * resources.displayMetrics.density).toInt()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad * 2, pad, pad)
            layoutParams = ViewGroup.LayoutParams(-1, -1)
        }

        root.addView(TextView(this).apply {
            text = "TipCal"; textSize = 28f; gravity = Gravity.CENTER
        })

        billInput = EditText(this).apply {
            hint = "Bill amount"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            textSize = 24f
        }
        root.addView(billInput)

        tipLabel = TextView(this).apply { textSize = 18f }
        tipBar = SeekBar(this).apply { max = 40; progress = 15 }
        splitLabel = TextView(this).apply { textSize = 18f }
        splitBar = SeekBar(this).apply { max = 19; progress = 0 } // 1..20 people
        root.addView(tipLabel); root.addView(tipBar)
        root.addView(splitLabel); root.addView(splitBar)

        tipAmount = TextView(this).apply { textSize = 20f }
        total = TextView(this).apply { textSize = 20f }
        perPerson = TextView(this).apply { textSize = 24f }
        root.addView(tipAmount); root.addView(total); root.addView(perPerson)

        setContentView(root)

        val update = { calculate() }
        billInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = update()
            override fun beforeTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
            override fun onTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
        })
        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, p: Int, fromUser: Boolean) = update()
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        }
        tipBar.setOnSeekBarChangeListener(listener)
        splitBar.setOnSeekBarChangeListener(listener)
        calculate()
    }

    private fun calculate() {
        val bill = billInput.text.toString().toDoubleOrNull() ?: 0.0
        val pct = tipBar.progress
        val people = splitBar.progress + 1
        val tip = bill * pct / 100.0
        val sum = bill + tip
        tipLabel.text = "Tip: $pct%"
        splitLabel.text = "Split between: $people"
        tipAmount.text = "Tip: ${money.format(tip)}"
        total.text = "Total: ${money.format(sum)}"
        perPerson.text = "Per person: ${money.format(sum / people)}"
    }
}
