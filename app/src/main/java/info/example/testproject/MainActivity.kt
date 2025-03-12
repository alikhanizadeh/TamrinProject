package info.example.testproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import info.example.testproject.Fragments.Fragmenttree
import info.example.testproject.Fragments.fragmentfour
import info.example.testproject.Fragments.fragmentone
import info.example.testproject.Fragments.fragmenttow
import info.example.testproject.databinding.ActivityMainBinding
import info.example.testproject.databinding.FragmentoneBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnFragmentone = findViewById<Button>(R.id.btnFragmentone)
        val btnFragmenttow = findViewById<Button>(R.id.btnFragmenttow)
        val btnFragmenttree = findViewById<Button>(R.id.btnFragmenttree)
        val btnFragmentfour = findViewById<Button>(R.id.btnFragmentfour)
        val btnFragmentfive = findViewById<Button>(R.id.btnFragmentfive)


        btnFragmentone.setOnClickListener{
            replaceFragment(fragmentone())
        }

        btnFragmenttow.setOnClickListener {
            replaceFragment(fragmenttow())
        }

        btnFragmenttree.setOnClickListener{
            replaceFragment(Fragmenttree())
        }

        btnFragmentfour.setOnClickListener{
            replaceFragment(fragmentfour())
        }

        //btnFragmentfive.setOnClickListener{
            //val intent = Intent(this,MainActivity::class.java)
            //startActivity(intent)
        //}






    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null) // برای برگشت به صفحه قبل
        transaction.commit()
    }


}