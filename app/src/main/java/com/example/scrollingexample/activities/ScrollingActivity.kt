package com.example.scrollingexample.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.scrollingexample.adapters.AdapterListPeople

import kotlinx.android.synthetic.main.scrolling_activity.*
import android.view.*
import com.example.scrollingexample.R
import com.example.scrollingexample.data.DataGenerator
import kotlin.random.Random


class ScrollingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrolling_activity)
        setSupportActionBar(prof_toolbar)

        initListPeople()

        val drawRandomImage = this.resources.obtainTypedArray(R.array.people_images)
            .getResourceId(Random.nextInt(10),-1)
        prof_ava_img.setImageResource(drawRandomImage)
    }

    private fun initListPeople() {
        val sheetBottom = layoutInflater.run { inflate(R.layout.sheet_basic,null) }
        val viewAdapter = AdapterListPeople(this,DataGenerator.getPeopleData(this), sheetBottom, bottom_sheet )
        val viewManager = LinearLayoutManager(this)
        prof_recycler_items.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            isLongClickable = true
            isClickable = true
        }    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}