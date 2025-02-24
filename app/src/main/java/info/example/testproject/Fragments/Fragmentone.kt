package info.example.testproject.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import info.example.testproject.R
import info.example.testproject.databinding.FragmentoneBinding

class fragmentone :Fragment() {
    private lateinit var binding: FragmentoneBinding
    lateinit var pref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)

        val Setting = pref.getString("setting", "متنی وجود ندارد")

        Toast.makeText(requireContext(), "$Setting", Toast.LENGTH_SHORT).show()

        binding.buttonFOne.setOnClickListener{
            val editor = pref.edit()
            editor.putString("setting","متن ذخیره شده")
            editor.apply()
            Toast.makeText(requireContext(), "داده ذخیره شد", Toast.LENGTH_SHORT).show()
        }

    }


}