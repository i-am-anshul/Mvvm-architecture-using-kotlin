package com.example.android.architecture

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.architecture.viewModel.DiceViewModel
import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.content_dice.*

private const val TAG = "DiceActivity"



class DiceActivity : AppCompatActivity() {
    private val imageViews by lazy { arrayOf<ImageView>(die1,die2,die3,die4,die5) }

    private lateinit var viewModel: DiceViewModel




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        viewModel = ViewModelProviders.of(this).get(DiceViewModel::class.java)

        viewModel.headline.observe(this, Observer {
            headline.text = it
        })
        viewModel.dice.observe(this, Observer {
            updateDisplay(it)
        })

        fab.setOnClickListener {
            viewModel.rollDice()
        }

        lifecycle.addObserver(MyLifeCycleObserver())

        val configChange =
            savedInstanceState?.getBoolean("configChange") ?: false

        if(configChange.not())
            viewModel.rollDice()
    }

    private fun updateDisplay(dice: IntArray) {
        for (i in 0 until imageViews.size) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.die_1;
                2 -> R.drawable.die_2;
                3 -> R.drawable.die_3;
                4 -> R.drawable.die_4;
                5 -> R.drawable.die_5;
                6 -> R.drawable.die_6;
                else -> R.drawable.die_6;
            }
            imageViews[i].setImageResource(drawableId)

        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("configChange",true)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dice,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_share -> shareResults()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareResults() : Boolean {

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"I Rolled the dice: ${viewModel.headline.value}")
            type = "text/plain"
        }

        startActivity(intent)





        return true
    }
}
