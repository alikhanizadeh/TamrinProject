package info.example.testproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import info.example.testproject.Dataclass.Users
import info.example.testproject.R

class AdapterRetrofit(private val list: List<Users>):BaseAdapter() {
    override fun getCount(): Int  = list.count()

    override fun getItem(p0: Int): Users = list[p0]


    override fun getItemId(p0: Int): Long = 0

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val holder : info.example.testproject.adapters.ViewHolder
        if (convertView == null){
            view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist_in_retrofit,null)
            holder = ViewHolder()
            holder.textName = view.findViewById(R.id.textName)
            holder.textFamily = view.findViewById(R.id.textFamily)
            holder.textAge = view.findViewById(R.id.textAge)
            view.tag = holder

        }else{
            holder = convertView.tag as info.example.testproject.adapters.ViewHolder
            view = convertView
        }
        val date  = getItem(position)
        holder.textName?.text = date.name
        holder.textFamily?.text = date.family
        holder.textAge?.text = date.age


        return view
    }

}
class ViewHolder{
    var textName : TextView? = null
    var textFamily : TextView? = null
    var textAge : TextView? = null
}