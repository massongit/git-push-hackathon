package io.github.massongit.hackathon.push.git.main.eventView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.massongit.hackathon.push.git.R
import io.github.massongit.hackathon.push.git.main.event.Event
import io.github.massongit.hackathon.push.git.util.launchCustomTab
import java.text.SimpleDateFormat
import java.util.*

/**
 * イベントビューのアダプター
 * @param context Activity
 */
class EventViewAdapter(private val context: Context) : RecyclerView.Adapter<EventViewHolder>() {
    companion object {
        /**
         * ログ用タグ
         */
        private val TAG = EventViewAdapter::class.simpleName
    }

    /**
     * イベントリスト
     */
    var items: List<Event> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        Log.v(EventViewAdapter.TAG, "onCreateViewHolder called")
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event, parent, false))
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        Log.v(EventViewAdapter.TAG, "onBindViewHolder called")
        holder.apply {
            val item = items[position]
            eventLayout.setOnClickListener {
                launchCustomTab(context, item.htmlUrl)
            }
            avatar.setImageDrawable(item.actorAvatar)
            message.text = item.message
            createdAt.text = SimpleDateFormat("yyyy/MM/dd (E) HH:mm:ss", Locale.getDefault()).format(item.createdAt)
        }
    }
}