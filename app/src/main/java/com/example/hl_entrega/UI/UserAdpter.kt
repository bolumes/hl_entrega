package com.example.hl_entrega.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hl_entrega.Models.User
import com.example.hl_entrega.databinding.UserItemBinding

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Updated ViewHolder to use View Binding
    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the layout using the binding class
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        // Bind the data using the binding class
        holder.binding.tvFullName.text = user.fullname
        holder.binding.tvPhoneNumber.text = user.phonenumber
        holder.binding.tvEmail.text = user.email
        holder.binding.tvAddress.text = user.address
    }

    override fun getItemCount() = userList.size
}
