package br.edu.ifce.crazyquiz.screens.special

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import br.edu.ifce.crazyquiz.R
import kotlinx.android.synthetic.main.activity_eduarda.*
import org.jetbrains.anko.bundleOf

class EduardaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eduarda)

        val messages = listOf(
                R.string.eduarda_m1,
                R.string.eduarda_m2,
                R.string.eduarda_m3,
                R.string.eduarda_m4,
                R.string.eduarda_m5,
                R.string.eduarda_m6,
                R.string.eduarda_m7,
                R.string.eduarda_m8,
                R.string.eduarda_m9,
                R.string.eduarda_m10
        ).map { getString(it) }

        viewPager.adapter = SlidePagerAdapter(messages, supportFragmentManager)
    }

    fun finishedSlide() {
        setResult(0)
        finish()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem != 0)
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
    }

    class SlidePagerAdapter(private val messages: List<String>, fm: FragmentManager)
        : FragmentPagerAdapter(fm) {

        override fun getCount() = messages.size

        override fun getItem(position: Int): Fragment {
            val last = position == count - 1
            val text = messages[position]
            return SlideItemFragment().apply {
                arguments = bundleOf("text" to text, "last" to last)
            }
        }

    }

}
