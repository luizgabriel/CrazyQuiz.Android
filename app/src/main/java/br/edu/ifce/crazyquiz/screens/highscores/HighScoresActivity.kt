package br.edu.ifce.crazyquiz.screens.highscores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameComplete
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameOver
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_high_scores.*
import kotlinx.android.synthetic.main.high_scores_list_item.*
import org.jetbrains.anko.*

class HighScoresActivity : AppCompatActivity(), IHighScoresView {

    private lateinit var presenter: HighScoresPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        highScoresList.setHasFixedSize(true)
        highScoresList.layoutManager = LinearLayoutManager(this)

        presenter = HighScoresPresenter(this, PlayerStore(applicationContext))
        presenter.onCreateView()

        val scores = intent.getIntExtra("scores", 0)
        val mode = intent.getSerializableExtra("mode") as FinishedGameMode?
        presenter.onGameFinished(scores, mode)
    }

    override fun showGameFinishedDialog(player: Player, mode: FinishedGameMode) {
        alert {
            titleResource = R.string.save_score
            isCancelable = false

            customView {
                verticalLayout(R.style.Base_V11_Theme_AppCompat_Light_Dialog) {
                    padding = dip(30)

                    textView {
                        textSize = 18f
                        textResource = when (mode) {
                            GameOver -> R.string.game_over
                            GameComplete -> R.string.game_complete
                        }
                    }

                    textView {
                        text = getString(R.string.final_scores, player.scores)
                        textSize = 24f
                    }

                    val name = editText {
                        setText(player.name)
                        hintResource = R.string.say_name
                        textSize = 18f
                    }

                    okButton {
                        presenter.onSaveScores(name.text.toString(), player.scores)
                    }
                }
            }

        }.show()
    }

    override fun setScoresList(players: List<Player>) {
        val adapter = HighScoresListAdapter(players)
        highScoresList.adapter = adapter
    }

    class HighScoresListAdapter(private val players: List<Player>) : RecyclerView.Adapter<HighScoresListAdapter.ViewHolder>() {

        class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            fun bind(player: Player) {
                highScoresName.text = player.name
                highScoresValue.text = player.scores.toString()
                highScoresPlace.text = """${adapterPosition + 1}º"""
            }
        }

        override fun getItemCount() = players.count()

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val player = players[position]
            holder?.bind(player)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.high_scores_list_item, parent, false)
            return ViewHolder(view)
        }
    }

}

