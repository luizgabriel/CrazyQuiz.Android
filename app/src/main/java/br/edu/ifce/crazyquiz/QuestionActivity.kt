package br.edu.ifce.crazyquiz

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.data.QuestionarySession
import br.edu.ifce.crazyquiz.presenters.QuestionPresenter
import br.edu.ifce.crazyquiz.views.IQuestionView

import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.withArguments

class QuestionActivity : AppCompatActivity(), IQuestionView {

    lateinit var mSectionsPagerAdapter: SectionsPagerAdapter
    lateinit var mViewPager: ViewPager
    lateinit var presenter: QuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        presenter = QuestionPresenter(this)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager.adapter = mSectionsPagerAdapter

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun setQuestionText(text: String) {
        TODO("not implemented")
    }

    override fun setQuestionNumber(number: Int) {
        TODO("not implemented")
    }

    override fun showHint(text: String) {
        TODO("not implemented")
    }

    override fun setQuestionOptions(options: Array<QuestionOption>) {
        TODO("not implemented")
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_question, container, false)
            val textView = rootView.findViewById(R.id.question_text) as TextView
            textView.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {

            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                return PlaceholderFragment()
                        .withArguments(ARG_SECTION_NUMBER to sectionNumber)
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 3
        }

    }
}
