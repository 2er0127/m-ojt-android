package com.eqst.vulnlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: MutableList<User>, private val userDatabase: UserDatabase) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.usernameTextView.text = "Username: ${currentUser.username}"
        holder.passwordTextView.text = "Password: ${currentUser.password}"

        holder.deleteButton.setOnClickListener {
            userDatabase.deleteUser(currentUser.username)
            userList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, userList.size)
        }
    }

    override fun getItemCount() = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val passwordTextView: TextView = itemView.findViewById(R.id.passwordTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
