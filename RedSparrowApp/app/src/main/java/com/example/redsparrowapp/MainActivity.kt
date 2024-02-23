package com.example.redsparrowapp

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.example.redsparrowapp.R
//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var coverImage: View
    private lateinit var menuButton: ImageButton
    private lateinit var movieStatus: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieRating: TextView
    private lateinit var descriptionButton: ImageButton
    private lateinit var day1: TextView
    private lateinit var day2: TextView
    private lateinit var day3: TextView
    private lateinit var day4: TextView
    private lateinit var day5: TextView
    private lateinit var day6: TextView
    private lateinit var day7: TextView
    private lateinit var date1: TextView
    private lateinit var date2: TextView
    private lateinit var date3: TextView
    private lateinit var date4: TextView
    private lateinit var date5: TextView
    private lateinit var date6: TextView
    private lateinit var date7: TextView
    private lateinit var dateSelector: View
    private lateinit var root: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)
        root= findViewById<ConstraintLayout>(R.id.root)
        addConstraintSetAnimation()
    }

    private fun addConstraintSetAnimation() {

        coverImage = findViewById(R.id.cover)
        menuButton = findViewById(R.id.menu_button)
        movieStatus = findViewById(R.id.status)
        movieTitle = findViewById(R.id.movie_title)
        movieDescription = findViewById(R.id.desc)
        movieRating = findViewById(R.id.rating)
        descriptionButton = findViewById(R.id.description_button)
        day1 = findViewById(R.id.day_1)
        day2 = findViewById(R.id.day_2)
        day3 = findViewById(R.id.day_3)
        day4 = findViewById(R.id.day_4)
        day5 = findViewById(R.id.day_5)
        day6 = findViewById(R.id.day_6)
        day7 = findViewById(R.id.day_7)
        date1 = findViewById(R.id.date_1)
        date2 = findViewById(R.id.date_2)
        date3 = findViewById(R.id.date_3)
        date4 = findViewById(R.id.date_4)
        date5 = findViewById(R.id.date_5)
        date6 = findViewById(R.id.date_6)
        date7 = findViewById(R.id.date_7)


        var isCoverView = false
        var isDescriptionView = false

        val initialConstraint = ConstraintSet()
        initialConstraint.clone(root)

        val coverConstraint = ConstraintSet()
        coverConstraint.clone(this, R.layout.cover_view)

        val descriptionConstraint = ConstraintSet()
        descriptionConstraint.clone(this, R.layout.description_view)

        val mapOfDays: Map<TextView, TextView> = mapOf(
            day1 to date1,
            day2 to date2,
            day3 to date3,
            day4 to date4,
            day5 to date5,
            day6 to date6,
            day7 to date7
        )

        val days: List<TextView> = listOf(day1, day2, day3, day4, day5, day6, day7)

        for (day in days) {
            day.setOnClickListener { selectDate(it as TextView, descriptionConstraint) }
        }

        for (day in mapOfDays) {
            day.value.setOnClickListener { selectDate(day.key, descriptionConstraint) }
        }

        coverImage.setOnClickListener {
            if (!isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                coverConstraint.applyTo(root)

                val anim = ValueAnimator()
                anim.setIntValues(Color.BLACK, Color.WHITE)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    menuButton.setColorFilter(it.animatedValue as Int)
                    movieStatus.setTextColor(it.animatedValue as Int)
                    movieTitle.setTextColor(it.animatedValue as Int)
                    movieDescription.setTextColor(it.animatedValue as Int)
                    movieRating.setTextColor(it.animatedValue as Int)
                    descriptionButton.setColorFilter(it.animatedValue as Int)
                }

                anim.duration = 300
                anim.start()
                isCoverView = true
                isDescriptionView = false
            }

        }

        menuButton.setOnClickListener {
            if (isCoverView) {
                TransitionManager.beginDelayedTransition(root)
                initialConstraint.applyTo(root)

                val anim = ValueAnimator()
                anim.setIntValues(Color.WHITE, Color.BLACK)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    menuButton.setColorFilter(it.animatedValue as Int)
                    movieStatus.setTextColor(it.animatedValue as Int)
                    movieTitle.setTextColor(it.animatedValue as Int)
                    movieDescription.setTextColor(it.animatedValue as Int)
                    movieRating.setTextColor(it.animatedValue as Int)
                    descriptionButton.setColorFilter(it.animatedValue as Int)
                }

                anim.duration = 300
                anim.start()
                isCoverView = false
                isDescriptionView = false
            } else if (isDescriptionView) {
                TransitionManager.beginDelayedTransition(root)
                initialConstraint.applyTo(root)

                isCoverView = false
                isDescriptionView = false
            }

        }

        descriptionButton.setOnClickListener {
            if (!isDescriptionView) {
                TransitionManager.beginDelayedTransition(root)
                descriptionConstraint.applyTo(root)

                if (isCoverView) {
                    val anim = ValueAnimator()
                    anim.setIntValues(Color.WHITE, Color.BLACK)
                    anim.setEvaluator(ArgbEvaluator())
                    anim.addUpdateListener {
                        menuButton.setColorFilter(it.animatedValue as Int)
                        movieStatus.setTextColor(it.animatedValue as Int)
                        movieTitle.setTextColor(it.animatedValue as Int)
                        movieDescription.setTextColor(it.animatedValue as Int)
                        movieRating.setTextColor(it.animatedValue as Int)
                        descriptionButton.setColorFilter(it.animatedValue as Int)
                    }

                    anim.duration = 300
                    anim.start()
                    isCoverView = false
                }


                isDescriptionView = true
            }
        }
    }

    private fun selectDate(day: TextView, destinationConstraint: ConstraintSet) {
        dateSelector = findViewById(R.id.date_selector)
        destinationConstraint.connect(
            dateSelector.id,
            ConstraintSet.START,
            day.id,
            ConstraintSet.START
        )
        destinationConstraint.connect(
            dateSelector.id,
            ConstraintSet.END,
            day.id,
            ConstraintSet.END
        )
        TransitionManager.beginDelayedTransition(root)
        destinationConstraint.applyTo(root)
    }
}
