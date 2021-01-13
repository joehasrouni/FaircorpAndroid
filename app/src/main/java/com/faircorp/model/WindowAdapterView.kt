package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R

class WindowAdapter (val listener: OnWindowSelectedListener) : RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() { // (1)


    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_window_name)
        val room: TextView = view.findViewById(R.id.txt_window_room)
        val status: TextView = view.findViewById(R.id.txt_status)
    }

    private val items = mutableListOf<WindowDto>() // (3)

    //populate windows list
    fun update(windows: List<WindowDto>) {  // (4)
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    // here we need a room list in order to display the room of a window based in the roomId in the window dto
    private val ritems = mutableListOf<RoomDto>()

    //populate rooms list
    fun updateRooms(rooms : List<RoomDto>) {
        ritems.clear()
        ritems.addAll(rooms)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {  // (7)
        val window = items[position]
      //  val roomItem = ritems[0]
        holder.apply {
            name.text = window.name
            status.text = window.windowStatus.toString()
       //     room.text = roomItem.name

            for (roomItem in ritems) {
                if (roomItem.id==window.roomId)
                    room.text = roomItem.name.toString()
            }

            itemView.setOnClickListener { listener.onWindowSelected(window.id) }

        }


    }

    override fun onViewRecycled(holder: WindowViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}