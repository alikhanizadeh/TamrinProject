package info.example.testproject.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.example.testproject.adapters.AdapterRetrofit
import info.example.testproject.Dataclass.Users
import info.example.testproject.R
import info.example.testproject.RetrofitService
import info.example.testproject.databinding.FragmenttreeBinding


class Fragmenttree :Fragment() , RetrofitService.OnUsersJsonRecieved {

    lateinit var binding: FragmenttreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmenttreeBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragmenttree,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitService().getUsers(this)

    }

    override fun onRecived(usersInfo: List<Users>) {
        val adapterTest = AdapterRetrofit(usersInfo)
        binding.listForRetrofit.adapter = adapterTest


    }
}
