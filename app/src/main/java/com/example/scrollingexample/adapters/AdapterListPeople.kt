package com.example.scrollingexample.adapters


import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.balysv.materialripple.MaterialRippleLayout
import com.example.scrollingexample.R
import com.example.scrollingexample.model.ProfilePeopleInList
import com.example.scrollingexample.utils.ItemAnimation
import de.hdodenhof.circleimageview.CircleImageView

import kotlinx.android.synthetic.main.scrolling_activity.*
import kotlinx.android.synthetic.main.sheet_basic.view.*


class AdapterListPeople(private val context: Context, private val values: List<ProfilePeopleInList>,private val bottomSheet: View,val Frl:FrameLayout) :
    RecyclerView.Adapter<AdapterListPeople.MyViewHolder>() {
    private var inf: LayoutInflater = LayoutInflater.from(context)

    private var mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null



    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inf.inflate(R.layout.prof__recycle_item, parent, false)
        var k = R.id.bottom_sheet
        mBehavior = BottomSheetBehavior.from(Frl)
        return MyViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("onBindViewHolder", "onBindViewHolder : " + position);
        val people = values[position]
        holder.listName?.text = people.name
        holder.listSurName?.text = people.surname
        holder.listImg?.setImageResource(people.img)
        holder.listFollowers?.text = people.followers.toString()
        holder.listClick?.setOnClickListener {
            showBottomSheetDialog(people)

         }
        setAnimation(holder.itemView, position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                on_attach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listName: TextView? = null
        var listSurName: TextView? = null
        var listFollowers: TextView? = null
        var listImg: CircleImageView? = null
        var listClick: MaterialRippleLayout? = null
        init {
            listName = itemView.findViewById(R.id.prof_list_name)
            listClick = itemView.findViewById(R.id.prof_list_click_label)
            listSurName = itemView.findViewById(R.id.prof_list_surname)
            listImg = itemView.findViewById(R.id.prof_list_img)
            listFollowers = itemView.findViewById(R.id.prof_list_followers)
        }

    }

    private var lastPosition = -1
    private var on_attach = true

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, if (on_attach) position else -1, 2)
            lastPosition = position
        }
    }

    private fun showBottomSheetDialog(people: ProfilePeopleInList) {

        if (mBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val bottomSheet = inf.run { inflate(R.layout.sheet_basic,null) }
        bottomSheet.bottom_sheet_name.text = people.name
        bottomSheet.bottom_sheet_followers.text = people.followers.toString()
        bottomSheet.bt_close.setOnClickListener{ mBottomSheetDialog?.dismiss()
        }

        bottomSheet.bt_details.setOnClickListener{
            Toast.makeText(context, "Details clicked", Toast.LENGTH_SHORT).show()
            }

        mBottomSheetDialog = BottomSheetDialog(context)

        mBottomSheetDialog?.setContentView(bottomSheet)
      //  mBottomSheetDialog?.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        mBottomSheetDialog?.show()
        mBottomSheetDialog?.setOnDismissListener { mBottomSheetDialog = null }
    }
}


