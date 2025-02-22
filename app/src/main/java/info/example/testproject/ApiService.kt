package info.example.testproject

import info.example.testproject.Dataclass.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("txt.json")
    fun getUsers() : Call<List<Users>>

}