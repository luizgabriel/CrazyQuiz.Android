package br.edu.ifce.crazyquiz

import android.content.Context
import android.media.MediaPlayer
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.os.Vibrator
import android.view.ViewGroup
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
    lateinit var lifeNumber: TextView
    lateinit var scoresNumber: TextView
    lateinit var levelNumber: TextView

    lateinit var presenter: QuestionPresenter

    var wrongPlayer: MediaPlayer? = null
    var rightPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        content = findViewById(R.id.main_content) as ViewGroup
        questionText = findViewById(R.id.question_text) as TextView
        questionNumber = findViewById(R.id.question_number) as TextView
        questionOptionsList = findViewById(R.id.question_options) as ListView
        questionOptionsList = findViewById(R.id.question_options) as ListView
        lifeNumber = findViewById(R.id.life_number) as TextView
        scoresNumber = findViewById(R.id.scores_number) as TextView
        levelNumber = findViewById(R.id.level_number) as TextView
        presenter = QuestionPresenter(this)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.onClick { presenter.onClickHelp() }
        questionOptionsList.onItemClick { adapterView, view, i, l -> presenter.onClickOption(i) }

    }

    override fun setWrongAudio(audio: IQuestionView.WrongAudio) {
        val id: Int
        when(audio) {
            IQuestionView.WrongAudio.BUZZER -> id = R.raw.wrong_buzzer
            IQuestionView.WrongAudio.DERP -> id = R.raw.wrong_derp
            IQuestionView.WrongAudio.FART -> id = R.raw.wrong_fart
            IQuestionView.WrongAudio.NOPE -> id = R.raw.wrong_nope
            IQuestionView.WrongAudio.REALLY_NIGGA -> id = R.raw.wrong_really_nigga
            IQuestionView.WrongAudio.SCREAM -> id = R.raw.wrong_scream
        }
        //wrongPlayer?.release()
        wrongPlayer = MediaPlayer.create(this, id);
    }

    override fun setRightAudio(audio: IQuestionView.RightAudio) {
        val id: Int
        when(audio) {
            IQuestionView.RightAudio.CHEERING -> id = R.raw.right_cheering
            IQuestionView.RightAudio.DING -> id = R.raw.right_ding
            IQuestionView.RightAudio.HEAVENLY -> id = R.raw.right_heavenly
        }
        //rightPlayer?.release()
        rightPlayer = MediaPlayer.create(this, id);
    }

    override fun onBackPressed() {
        presenter.onBackButtonPressed()
    }

    override fun callFinishedGameScreen() {
        toast(getString(R.string.game_complete))
        finish()
    }

    override fun callGameOverScreen() {
        toast(getString(R.string.game_over))
        finish()
    }

    override fun showHint(hint: String) {
        Snackbar.make(content, hint, Snackbar.LENGTH_LONG).show()
    }

    override fun showNoHintMessage() {
        Snackbar.make(content, getString(R.string.no_hint), Snackbar.LENGTH_SHORT).show()
    }

    override fun setQuestionText(text: String) {
        questionText.text = text
    }

    override fun setQuestionNumber(number: Int) {
        questionNumber.text = number.toString()
    }

    override fun setQuestionLevel(level: Int) {
        levelNumber.text = level.toString()
    }

    override fun setLifeCount(life: Int) {
        lifeNumber.text = life.toString()
    }

    override fun setScoresCount(scores: Int) {
        scoresNumber.text = scores.toString()
    }

    override fun setQuestionOptions(options: List<QuestionOption>) {
        val opts = options.map { it.text }
        questionOptionsList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, opts)
    }

    override fun notifyRightAnswer() {
        rightPlayer?.start()
    }

    override fun notifyWrongAnswer() {
        toast(R.string.less_one_life)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (vibrator != null && vibrator.hasVibrator())
            vibrator.vibrate(100)

        wrongPlayer?.start()
    }


}

