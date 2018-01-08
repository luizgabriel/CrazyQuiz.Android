package br.edu.ifce.crazyquiz.screens.question

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameComplete
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameOver
import br.edu.ifce.crazyquiz.screens.highscores.HighScoresActivity
import kotlinx.android.synthetic.main.activity_question.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.toast

class QuestionActivity : AppCompatActivity(), IQuestionView {

    private lateinit var presenter: QuestionPresenter
    private var wrongPlayer: MediaPlayer? = null
    private var rightPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionOptions.onItemClick { _, _, i, _ -> presenter.onClickOption(i) }

        presenter = QuestionPresenter(this, QuestionStore(applicationContext))
        presenter.onCreateView()
    }

    override fun setWrongAudio(audioId: Int) {
        wrongPlayer = MediaPlayer.create(this, audioId)
    }

    override fun setRightAudio(audioId: Int) {
        rightPlayer = MediaPlayer.create(this, audioId)
    }

    override fun onBackPressed() {
        presenter.onBackButtonPressed()
    }

    override fun callFinishedGameScreen(scores: Int, mode: QuestionnaireSession.FinishedGameMode) {
        when (mode) {
            GameOver -> toast(getString(R.string.game_over))
            GameComplete -> toast(getString(R.string.game_complete))
        }

        startActivity(intentFor<HighScoresActivity>(
                "mode" to mode,
                "scores" to scores
        ))
    }

    override fun setQuestionText(text: String) {
        questionText.text = text
    }

    override fun setQuestionNumber(number: Int) {
        questionNumber.text = number.toString()
    }

    override fun setLifeCount(life: Int) {
        lifeNumber.text = life.toString()
    }

    override fun setScoresCount(scores: Int) {
        scoresNumber.text = scores.toString()
    }

    override fun setQuestionOptions(options: List<QuestionOption>) {
        val opts = options.map { it.text }
        questionOptions.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, opts)
    }

    override fun notifyRightAnswer() {
        rightPlayer?.start()
    }

    override fun notifyWrongAnswer() {
        toast(R.string.less_one_life)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (vibrator != null && vibrator.hasVibrator())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot(300, 1))
            else
                vibrator.vibrate(300)

        wrongPlayer?.start()
    }


}

