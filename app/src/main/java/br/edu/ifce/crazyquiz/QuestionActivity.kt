package br.edu.ifce.crazyquiz

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import android.widget.TextView
import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.presenters.QuestionPresenter
import br.edu.ifce.crazyquiz.views.IQuestionView

import org.jetbrains.anko.*

class QuestionActivity : AppCompatActivity(), IQuestionView {

    lateinit var content: ViewGroup
    lateinit var questionText: TextView
    lateinit var questionNumber: TextView
    lateinit var questionOptionsList: ListView

    lateinit var presenter: QuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        content = findViewById(R.id.main_content) as ViewGroup
        questionText = findViewById(R.id.question_text) as TextView
        questionNumber = findViewById(R.id.question_number) as TextView
        questionOptionsList = findViewById(R.id.question_options) as ListView
        questionOptionsList = findViewById(R.id.question_options) as ListView
        val fab = findViewById(R.id.fab) as FloatingActionButton

        fab.onClick { presenter.onClickHelp() }
        questionOptionsList.onItemClick { adapterView, view, i, l -> presenter.onClickOption(i) }

        presenter = QuestionPresenter(this)
    }

    override fun showHint(hint: String) {
        Snackbar.make(content, hint, Snackbar.LENGTH_LONG).show()
    }

    override fun setQuestionText(text: String) {
        questionText.text = text
    }

    override fun setQuestionNumber(number: Int) {
        questionNumber.text = number.toString()
    }

    override fun setQuestionLevel(level: Int) {
        //
    }

    override fun setQuestionOptions(options: List<QuestionOption>) {
        val opts = options.map { it.text }
        questionOptionsList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, opts)
    }

    override fun notifyWrongAnswer() {
        toast(R.string.less_one_life)
    }

}

