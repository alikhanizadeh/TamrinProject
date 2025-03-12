package info.example.roomdatabase.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import info.example.roomdatabase.R
import info.example.roomdatabase.viewmodel.UserViewModel
import info.example.roomdatabase.databinding.FragmentListBinding


class listFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentListBinding.inflate(inflater,container ,false)

        //RecyclerView
        val adapter  = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(user)

        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment2)
        }

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){

            deleteAllUsers()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_,->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()


        }
        builder.setNegativeButton("No"){_,_,->}
        builder.setTitle("Delete everything? ")
        builder.setMessage("Are you sure you want to delete everything? ")
        builder.create().show()
    }


}