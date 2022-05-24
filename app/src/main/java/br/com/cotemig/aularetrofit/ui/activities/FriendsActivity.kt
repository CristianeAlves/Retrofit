package br.com.cotemig.aularetrofit.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import br.com.cotemig.aularetrofit.R
import br.com.cotemig.aularetrofit.models.Friend
import br.com.cotemig.aularetrofit.services.RetrofitInitializer
import br.com.cotemig.aularetrofit.ui.adapters.FriendAdapter
import retrofit2.Call
import retrofit2.Response

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        getFriends()
    }

    fun getFriends(){

        var token = intent.getStringExtra("token")

        token?.let{

        var s = RetrofitInitializer().friendService()
        var call = s.getFriends(it)

        call.enqueue(object : retrofit2.Callback<List<Friend>>{
            override fun onResponse(call: Call<List<Friend>>, response: Response<List<Friend>>) {


                response.body()?.let {

                    showListView(it)
                    //Toast.makeText(this@MainActivity, "Amigos: ${it.size}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Friend>>, t: Throwable) {

            }
        })
      }
    }

    fun showListView(list: List<Friend>){

        var friends = findViewById<ListView>(R.id.friends)
        friends.adapter = FriendAdapter(this, list)

    }
}