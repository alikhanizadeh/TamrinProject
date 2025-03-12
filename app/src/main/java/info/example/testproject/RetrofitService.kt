package info.example.testproject

import android.util.Log
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
                if ((response.isSuccessful)) {
                    val value = response.body()
                    if (value != null)
                        onUsersJsonRecieved.onRecived(value)
                }else{
                    Log.e("RetrofitService", "Response error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                t.printStackTrace()  // خطا رو در Logcat نشون می‌ده
            }

        })
    }

    interface OnUsersJsonRecieved{
        fun onRecived(usersInfo : List<Users>)
    }
}