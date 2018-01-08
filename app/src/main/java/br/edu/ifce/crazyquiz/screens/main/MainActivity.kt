package br.edu.ifce.crazyquiz.screens.main

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.screens.highscores.HighScoresActivity
import br.edu.ifce.crazyquiz.screens.question.QuestionActivity
import br.edu.ifce.crazyquiz.screens.question.QuestionStore
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var presenter: MainPresenter
    private lateinit var processDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processDialog = indeterminateProgressDialog(R.string.first_loading) {
            setCancelable(false)
        }

        startBtn.onClick { presenter.onClickStartGame() }
        scoresBtn.onClick { presenter.onClickHighScores() }
        shareBtn.onClick { presenter.onClickShare() }

        presenter = MainPresenter(this, QuestionStore(applicationContext))
        presenter.onCreateView()
    }

    override fun startGameScreen() {
        startActivity<QuestionActivity>()
    }

    override fun openHighScoresScreen() {
        startActivity<HighScoresActivity>()
    }

    override fun openShareScreen() {
        //
    }

    override fun errorOnLoadQuestions() {
        toast(R.string.service_unavailable)
        if (processDialog.isShowing) processDialog.hide()
        finish()
    }

    override fun startLoading() {
        startBtn.isEnabled = false
        if (!processDialog.isShowing) processDialog.show()
    }

    override fun finishLoading() {
        startBtn.isEnabled = true
        if (processDialog.isShowing) processDialog.hide()
    }
}
