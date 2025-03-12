package info.example.testproject.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import info.example.testproject.databinding.FragmentfourBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class fragmentfour : Fragment() {

    private lateinit var binding: FragmentfourBinding
    private val apiKey = "08bfd544cb06c8124fd1ae7ecd75f93b"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentfourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApi.setOnClickListener {
            val cityName = binding.EdittextApi.text.toString().trim()
            if (cityName.isEmpty()) {
                Toast.makeText(context, "لطفا نام شهر رو وارد کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // استفاده از Coroutine به جای AsyncTask
            lifecycleScope.launch {
                fetchWeatherData(cityName)
            }
        }
    }

    // تابعی که اطلاعات آب و هوا رو میگیره
    private suspend fun fetchWeatherData(cityName: String) {
        val api = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$apiKey&lang=fa&units=metric"

        withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(api).build()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("خطا در برقراری ارتباط: ${response.code}")

                    val responseBody = response.body?.string() ?: throw IOException("داده‌ای دریافت نشد")
                    parseWeatherData(responseBody)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "خطا در ارتباط با سرور!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // تابعی که داده‌های JSON رو تجزیه می‌کنه
    private suspend fun parseWeatherData(json: String) {
        withContext(Dispatchers.Main) {
            try {
                val gson = Gson()
                val weatherResponse = gson.fromJson(json, WeatherResponse::class.java)

                val desc = weatherResponse.weather[0].description
                val humidity = weatherResponse.main.humidity
                val speed = weatherResponse.wind.speed

                binding.txtConditionApi.text = desc
                binding.txtWetApi.text = humidity.toString()
                binding.txtSpeedApi.text = speed.toString()

            } catch (e: Exception) {
                Toast.makeText(context, "خطا در تجزیه داده‌ها!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // مدل داده‌ای برای پارس JSON
    data class WeatherResponse(
        val weather: List<Weather>,
        val main: Main,
        val wind: Wind
    )

    data class Weather(val description: String)
    data class Main(val humidity: Int)
    data class Wind(val speed: Float)
}
