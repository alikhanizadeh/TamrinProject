package info.example.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import info.example.roomdatabase.R
import info.example.roomdatabase.model.User
import info.example.roomdatabase.viewmodel.UserViewModel
import info.example.roomdatabase.databinding.FragmentAddBinding


class addFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBinding.inflate(inflater,container,false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addBtn.setOnClickListener{
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if (inputCheck(firstName,lastName,age)){
            // Create User Object
            val user  = User(0,firstName,lastName, Integer.parseInt(age.toString()))
            //add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully  added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Please fill out all fields. ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName :String ,lastNam :String , age : Editable) : Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastNam) && age.isEmpty())
    }


}