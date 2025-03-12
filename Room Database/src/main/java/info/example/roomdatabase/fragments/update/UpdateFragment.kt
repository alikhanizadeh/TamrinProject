package info.example.roomdatabase.fragments.update

import android.annotation.SuppressLint
import android.os.Bundle
import android.service.autofill.BatchUpdates
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import info.example.roomdatabase.R
import info.example.roomdatabase.databinding.FragmentUpdateBinding
import info.example.roomdatabase.model.User
import info.example.roomdatabase.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
   private lateinit var binding: FragmentUpdateBinding
   private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFirstNameEt.setText(args.currentUser.firstname)
        binding.updateLastNameEt.setText(args.currentUser.lastname)
        binding.updateAgeEt.setText(args.currentUser.age.toString())

        binding.updateBtn.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem(){
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = Integer.parseInt(binding.updateAgeEt.text.toString())
        if (inputCheck(firstName,lastName,binding.updateAgeEt.text)){
            val updatedUser = User(args.currentUser.id,firstName,lastName,age)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName :String ,lastNam :String , age : Editable) : Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastNam) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_,->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed:${args.currentUser.firstname}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_,->}
        builder.setTitle("Delete ${args.currentUser.firstname}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstname}?")
        builder.create().show()

    }
}