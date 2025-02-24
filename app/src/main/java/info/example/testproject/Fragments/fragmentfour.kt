package info.example.testproject.Fragments

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import info.example.testproject.R
import info.example.testproject.databinding.FragmentfourBinding
import info.example.testproject.databinding.FragmentoneBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class fragmentfour() :Fragment() {
    lateinit var binding: FragmentfourBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentfourBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApi.setOnClickListener{

            if (binding.EdittextApi.text.toString() == ""){
                Toast.makeText(context, "لطفا نام شهر رو وارد کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            getData(binding.EdittextApi.text.toString())

        }
    }

    private fun getData(cityName : String) {

        val api = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&apikey=08bfd544cb06c8124fd1ae7ecd75f93b"
        MyAsyncTask().execute(api)
    }
    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask:AsyncTask<String,String,String>(){

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: String?): String {
            val url = URL(p0[0])
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            val json = converStreamToString(connection.inputStream)
            publishProgress(json)
            return ""
        }

        @SuppressLint("SetTextI18n")
        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            val jsonObject = JSONObject(values[0].toString())
            val weather = jsonObject.getJSONArray("weather")
            val wind = jsonObject.getJSONObject("wind")
            val main = jsonObject.getJSONObject("main")
            val array = weather.getJSONObject(0)


            val desc = array.getString("description")
            val humidity = main.getInt("humidity")
            val speed = wind.getBoolean("speed")

            binding.txtConditionApi.text = desc
            binding.txtWetApi.text = humidity.toString()
            binding.txtSpeedApi.text = speed.toString()




        }



    }

    private fun converStreamToString(stream :InputStream) : String{
        val bufferReader = BufferedReader(InputStreamReader(stream))
        var line:String
        val sb = StringBuilder()

        try {
            do {
                line = bufferReader.readLine()
                sb.append(line)
            }while (true)
        }catch (ex : Exception){
            ex.printStackTrace()
        }
        return  sb.toString()
    }


}