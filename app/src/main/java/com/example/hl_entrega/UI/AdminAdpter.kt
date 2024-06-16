package com.example.hl_entrega

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hl_entrega.Models.Admin
import com.example.hl_entrega.databinding.AdminItemBinding

class AdminAdapter(private val adminList: List<Admin>) : RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {

    // Updated ViewHolder to use View Binding
    class AdminViewHolder(val binding: AdminItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        // Inflate the layout using the binding class
        val binding = AdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val admin = adminList[position]
        // Bind the data using the binding class
        holder.binding.tvFullName.text = admin.fullname
        holder.binding.tvPhoneNumber.text = admin.phonenumber
        holder.binding.tvEmail.text = admin.email
        holder.binding.tvAddress.text = admin.address
    }

    override fun getItemCount() = adminList.size
}
