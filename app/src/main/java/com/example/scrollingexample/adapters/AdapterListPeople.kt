package com.example.scrollingexample.adapters


import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout
import com.example.scrollingexample.R
import com.example.scrollingexample.model.ProfilePeopleInList
import de.hdodenhof.circleimageview.CircleImageView

class AdapterListPeople(private val context: Context, private val values: List<ProfilePeopleInList>) :
    RecyclerView.Adapter<AdapterListPeople.MyViewHolder>() {
    private var context_ = context
    private var inf: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListPeople.MyViewHolder {
        val itemView = inf.inflate(R.layout.prof__recycle_item, parent, false)
        itemView.findViewById<MaterialRippleLayout>(R.id.prof_list_click_label)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val people = values[position]
        holder.listName?.text = people.name
        holder.listSurName?.text = people.surname
        holder.listImg?.setImageResource(people.img)
        holder.listClick?.setOnClickListener {
            Snackbar.make(holder.itemView,"clicked ${people.name}",Snackbar.LENGTH_SHORT).show()
         }
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listName: TextView? = null
        var listSurName: TextView? = null
        var listImg: CircleImageView? = null
        var listClick: MaterialRippleLayout? = null
        init {
            listName = itemView.findViewById(R.id.prof_list_name)
            listClick = itemView.findViewById(R.id.prof_list_click_label)
            listSurName = itemView.findViewById(R.id.prof_list_surname)
            listImg = itemView.findViewById(R.id.prof_list_img)
        }

    }


}


