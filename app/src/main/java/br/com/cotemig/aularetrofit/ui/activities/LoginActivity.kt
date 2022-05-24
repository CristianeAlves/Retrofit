package br.com.cotemig.aularetrofit.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.cotemig.aularetrofit.R
import br.com.cotemig.aularetrofit.models.Account
import br.com.cotemig.aularetrofit.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login(){
        var s = RetrofitInitializer().accountService()

        var account = Account()
        var login = findViewById<EditText>(R.id.login)
        var password = findViewById<EditText>(R.id.password)
        account.email = login.text.toString()
        account.password = password.text.toString()

        var call = s.auth(account)

        call.enqueue(object: retrofit2.Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {

                if(response.code() == 200){

                    response.body()?.let{
                        showFriends(it)
                    }


                    Toast.makeText(this@LoginActivity, "Tudo certo", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario ou senha invalidos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "OPS", Toast.LENGTH_LONG).show()

            }
        })
    }

    fun showFriends(account: Account){
        var intent = Intent(this, FriendsActivity::class.java)
        intent.putExtra("token", account.token)
        startActivity(intent)
    }
}