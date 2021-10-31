package com.example.tuwaiqcapstone1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tuwaiqcapstone1.Repository.TaskRepository
private lateinit var navcontrol: NavController
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TaskRepository.init(this)

        val nav=supportFragmentManager.findFragmentById(R.id.FragmentViewInMainActivity) as NavHostFragment
        navcontrol=nav.navController
        setupActionBarWithNavController(navcontrol)
    }

    override fun onSupportNavigateUp(): Boolean {
         return navcontrol.navigateUp()||super.onSupportNavigateUp()

    }
}