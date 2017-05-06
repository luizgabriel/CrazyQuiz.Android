package br.edu.ifce.crazyquiz

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import br.edu.ifce.crazyquiz.presenters.MainPresenter
import br.edu.ifce.crazyquiz.views.IMainView
import org.jetbrains.anko.onClick
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var presenter: MainPresenter
    private lateinit var startBtn: FloatingActionButton
    private lateinit var scoresBtn: FloatingActionButton
    private lateinit var shareBtn: FloatingActionButton
    private lateinit var processDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.start_btn) as FloatingActionButton
        scoresBtn = findViewById(R.id.scores_btn) as FloatingActionButton
        shareBtn = findViewById(R.id.share_btn) as FloatingActionButton
        processDialog = indeterminateProgressDialog(R.string.first_loading)
        presenter = MainPresenter(this)

        startBtn.onClick { presenter.onClickStartGame() }
        scoresBtn.onClick { presenter.onClickHighScores() }
        shareBtn.onClick { presenter.onClickShare() }

    }

    override fun startGameScreen() {
        startActivity<QuestionActivity>()
    }

    override fun openHighScoresScreen() {
        //
    }

    override fun openShareScreen() {
        //
    }

    override fun errorOnLoadQuestions() {
        toast(R.string.service_unavailable)
        finish()
    }

    override fun startLoading() {
        startBtn.isEnabled = false
        processDialog.show()
    }

    override fun finishLoading() {
        startBtn.isEnabled = true
        processDialog.hide()
    }
}
