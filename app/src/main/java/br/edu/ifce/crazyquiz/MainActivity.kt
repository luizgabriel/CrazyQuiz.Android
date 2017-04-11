package br.edu.ifce.crazyquiz

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import br.edu.ifce.crazyquiz.presenters.MainPresenter
import br.edu.ifce.crazyquiz.views.IMainView
import org.jetbrains.anko.onClick
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById(R.id.start_btn) as FloatingActionButton
        val scoresBtn = findViewById(R.id.scores_btn) as FloatingActionButton
        val shareBtn = findViewById(R.id.share_btn) as FloatingActionButton
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

}
