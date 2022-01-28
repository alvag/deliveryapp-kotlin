package com.maxalva.deliveryapp.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxalva.deliveryapp.R
import com.maxalva.deliveryapp.Utils.SharedPref
import com.maxalva.deliveryapp.activities.client.home.ClientHomeActivity
import com.maxalva.deliveryapp.activities.delivery.home.DeliveryHomeActivity
import com.maxalva.deliveryapp.activities.restaurant.home.RestaurantHomeActivity
import com.maxalva.deliveryapp.models.Role

class RolesAdapter(private val context: Activity, private val roles: ArrayList<Role>): RecyclerView.Adapter<RolesAdapter.RolesViewHolder>() {

    private var sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_roles, parent, false)
        return RolesViewHolder(view);
    }

    override fun onBindViewHolder(holder: RolesViewHolder, position: Int) {
        val role = roles[position]

        holder.textViewRole.text = role.name;
        Glide.with(context).load(role.image).into(holder.imageView)

        holder.itemView.setOnClickListener {
            goToRoleView(role)
        }
    }

    private fun goToRoleView(role: Role) {
        sharedPref.save("role", role.name);

        when (role.name) {
            "RESTAURANTE" -> {
                val i = Intent(context, RestaurantHomeActivity::class.java)
                context.startActivity(i)
            }
            "CLIENTE" -> {
                val i = Intent(context, ClientHomeActivity::class.java)
                context.startActivity(i)
            }
            "REPARTIDOR" -> {
                val i = Intent(context, DeliveryHomeActivity::class.java)
                context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    class RolesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewRole: TextView = view.findViewById(R.id.textview_role)
        val imageView: ImageView = view.findViewById(R.id.imageview_role)

    }

}