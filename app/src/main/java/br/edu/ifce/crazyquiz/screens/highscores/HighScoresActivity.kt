package br.edu.ifce.crazyquiz.screens.highscores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.Player

class HighScoresActivity : AppCompatActivity(), IHighScoresView {

    private val presenter = HighScoresPresenter(this)

    private lateinit var highScoresList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        highScoresList = findViewById(R.id.high_scores_list) as RecyclerView
        highScoresList.setHasFixedSize(true)

        val highScoresLayoutManager = LinearLayoutManager(this)
        highScoresList.layoutManager = highScoresLayoutManager

        presenter.onCreateView()
    }

    override fun setScoresList(players: List<Player>) {
        val adapter = HighScoresListAdapter(players)
        highScoresList.adapter = adapter
    }

    class HighScoresListAdapter(val players: List<Player>) : RecyclerView.Adapter<HighScoresListAdapter.ViewHolder>() {

        val TYPE_MAJOR = 0
        val TYPE_MINOR = 1

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val name = view.findViewById(R.id.high_scores_name) as TextView
            val value = view.findViewById(R.id.high_scores_value) as TextView
            val place = view.findViewById(R.id.high_scores_place) as TextView
        }

        override fun getItemCount() = players.count()

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val player = players[position]
            holder?.name?.text = player.name
            holder?.value?.text = player.scores.toString()
            holder?.place?.text = """${position + 1}º"""
        }

        override fun getItemViewType(position: Int) = if (position < 3) TYPE_MAJOR else TYPE_MINOR

        private fun getListItemResource(viewType: Int): Int {
            when (viewType) {
                TYPE_MAJOR -> return R.layout.high_scores_list_item
                TYPE_MINOR -> return R.layout.high_scores_list_secondary_item
            }

            throw NotImplementedError()
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(getListItemResource(viewType), parent, false)
            val viewHolder = ViewHolder(view)
            return viewHolder
        }
    }


}

