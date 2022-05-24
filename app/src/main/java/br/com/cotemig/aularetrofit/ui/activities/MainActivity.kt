package br.com.cotemig.aularetrofit.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import br.com.cotemig.aularetrofit.R
import br.com.cotemig.aularetrofit.models.Friend
import br.com.cotemig.aularetrofit.services.RetrofitInitializer
import br.com.cotemig.aularetrofit.ui.adapters.FriendAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}