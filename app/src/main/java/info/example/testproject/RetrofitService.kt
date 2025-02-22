package info.example.testproject

import info.example.testproject.Dataclass.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RetrofitService {
    fun getUsers(onUsersJsonRecieved: OnUsersJsonRecieved){
        val callBack = ApiClient().getClient().create(ApiService::class.java).getUsers()
        callBack.enqueue(object : Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                val value = response.body()
                if (value != null)
                    onUsersJsonRecieved.onRecived(value)

            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {

            }

        })
    }

    interface OnUsersJsonRecieved{
        fun onRecived(usersInfo : List<Users>)
    }
}