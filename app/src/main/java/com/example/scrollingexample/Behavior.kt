package com.example.scrollingexample

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import de.hdodenhof.circleimageview.CircleImageView

class AvatarImageBehavior(private val mContext: Context,attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<CircleImageView>() {

    private val defBottom = mContext.resources.getDimension(R.dimen.prof_layout_buttons_height)+
            mContext.resources.getDimension(R.dimen.prof_layout_name_height)

    private var param: MoveImageParameters = MoveImageParameters(
        expSize = mContext.resources.getDimension(R.dimen.ava_expanded_size),
        collSize = mContext.resources.getDimension(R.dimen.ava_collapsed_size),
        expLeftPosition = mContext.resources.getDimension(R.dimen.ava_expanded_left_pos),
        collLeftPosition = mContext.resources.getDimension(R.dimen.ava_collapsed_left_pos),
        pointMoveToCollaps = mContext.resources.getDimension(R.dimen.ava_point_to_move),
        collTopPosition = mContext.resources.getDimension(R.dimen.ava_collapsed_top_margin)
    )

    init{ param.expBottomPosition = param.expSize/2 }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: CircleImageView, dependency: View): Boolean {
        return dependency is LinearLayout
    }

    private lateinit var ll: LinearLayout
    private lateinit var lp: LinearLayout.LayoutParams
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: CircleImageView, dependency: View): Boolean {

        if (dependency is AppBarLayout) //go to linearLayout
            ll = dependency
            if (ll.getChildAt(2) is LinearLayout)
                ll = ll.getChildAt(2) as LinearLayout

        if (ll.layoutParams is LinearLayout.LayoutParams)
            lp = ll.layoutParams as LinearLayout.LayoutParams

        if (dependency.y >= 0)
            if ((dependency.y - defBottom )<= param.pointMoveToCollaps) {                                    //collapsed

                val currentCollapsedFactor = 1 -( dependency.y - defBottom)/ param.pointMoveToCollaps//0-exp,1-coll
                val childLp = child.layoutParams as CoordinatorLayout.LayoutParams?

                                                                                                            // scale ava
                childLp?.width = (param.expSize - (param.expSize - param.collSize)*currentCollapsedFactor).toInt()
                childLp?.height = (param.expSize - (param.expSize - param.collSize)*currentCollapsedFactor).toInt()

                                                                                                // move to left position
                child.x = param.expLeftPosition - (param.expLeftPosition - param.collLeftPosition)*currentCollapsedFactor
                child.y = dependency.y - param.expBottomPosition - defBottom +
                        (param.expBottomPosition + param.collTopPosition)*currentCollapsedFactor
                                                                                           // move Text to left position
                lp.marginStart = (child.x + child.width + param.collLeftPosition).toInt()
                if (childLp != null) child.layoutParams = childLp
            } else {                                                                                         // expanded
                val childLp = child.layoutParams as CoordinatorLayout.LayoutParams?

                child.x = param.expLeftPosition
                child.y = dependency.y - defBottom - param.expBottomPosition

                childLp?.width  = param.expSize.toInt()
                childLp?.height = param.expSize.toInt()

                lp.marginStart = (child.x + param.expSize + param.collLeftPosition).toInt()
                if (childLp != null) child.layoutParams = childLp
            }
            ll.layoutParams = lp

            return true
        }
}

class MoveImageParameters( //exp - Expanded, coll - Collapsed
    var pointMoveToCollaps: Float = 100f,
    var expSize:            Float = 100f,
    var collSize:           Float =  80f,
    var expLeftPosition:    Float = 100f,
    var collLeftPosition:   Float =  30f,
    var expBottomPosition:  Float = expSize/2,
    var collTopPosition:    Float = 10f
)
