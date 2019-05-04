package com.example.scrollingexample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.scrollingexample.model.ProfilePeopleInList
import com.example.scrollingexample.adapters.AdapterListPeople
import kotlinx.android.synthetic.main.scrolling_activity.*

class ScrollingActivity : AppCompatActivity() {

    companion object {
        fun getAppContext(): Context {
            return getAppContext()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrolling_activity)
        setSupportActionBar(prof_toolbar)

        val listProfPeople = listOf(
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.avatar),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person),
            ProfilePeopleInList("Name","Surname",R.drawable.ic_person)
        )

        val viewAdapter = AdapterListPeople(this,listProfPeople )
        val viewManager = LinearLayoutManager(this)
        prof_recycler_items.apply {

            layoutManager = viewManager
            adapter = viewAdapter
            isLongClickable = true
            isClickable = true
        }
    }

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