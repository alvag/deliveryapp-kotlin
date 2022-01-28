package com.maxalva.deliveryapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.models.Role

class RolesAdapter(private val context: Activity, private val roles: ArrayList<Role>): RecyclerView.Adapter<RolesAdapter.RolesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_roles, parent, false)
        return RolesViewHolder(view);
    }

    override fun onBindViewHolder(holder: RolesViewHolder, position: Int) {
        val role = roles[position]

        holder.textViewRole.text = role.name;
        Glide.with(context).load(role.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    class RolesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewRole: TextView = view.findViewById(R.id.textview_role)
        val imageView: ImageView = view.findViewById(R.id.imageview_role)

    }

}