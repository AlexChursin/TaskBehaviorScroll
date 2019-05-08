package com.example.scrollingexample.data

import android.R.attr.name
import android.support.v4.content.res.TypedArrayUtils.getResourceId
import android.provider.Contacts.People
import android.content.Context
import android.content.res.TypedArray
import com.example.scrollingexample.R
import com.example.scrollingexample.model.ProfilePeopleInList
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class DataGenerator {

companion object{
    fun getPeopleData(ctx: Context): List<ProfilePeopleInList> {
        val items: ArrayList<ProfilePeopleInList> = ArrayList()
        val drw_arr = ctx.resources.obtainTypedArray(R.array.people_images)
        val name_arr = ctx.resources.getStringArray(R.array.people_names)

        for (i in 0 until name_arr.size) {
            items.add(
                ProfilePeopleInList(
                    name = name_arr[i],
                    img = drw_arr.getResourceId(i,-1),
                    surname = "",
                    followers = Random.nextInt(100))
            )
        }
        return items
    }
}
}