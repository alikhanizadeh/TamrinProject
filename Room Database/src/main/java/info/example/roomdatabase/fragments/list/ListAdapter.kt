package info.example.roomdatabase.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import info.example.roomdatabase.R
import info.example.roomdatabase.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text  = currentItem.firstname
        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItem.lastname
        holder.itemView.findViewById<TextView>(R.id.age_txt).text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("user", currentItem)
            }
            holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
