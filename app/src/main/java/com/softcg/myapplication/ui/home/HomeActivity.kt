package com.softcg.myapplication.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.softcg.myapplication.R

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var addtarea:View
    private lateinit var addevento:View
    private lateinit var addcalificacion:View
    private var rotate=false


    companion object {
        fun create(context: Context): Intent =
            Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initNavegate()
        initfloatingBottons()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }

    fun initNavegate(){
        toolbar=findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)

        drawerLayout=findViewById(R.id.drawer)
        navigationView=findViewById(R.id.nav_view)

        navController=findNavController(R.id.fragmentContainerView)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,  // Use toolbar reference here
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.id_home_fragment, R.id.id_diary_fragment, R.id.id_subjects_fragment,
                R.id.id_help_fragment, R.id.id_schedule_fragment, R.id.id_ratings_fragment),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }

    fun initfloatingBottons(){

        addtarea= findViewById(R.id.addTarea)
        addevento= findViewById(R.id.addEvento)
        addcalificacion= findViewById(R.id.addCalificacion)
        initShowOut(addtarea)
        initShowOut(addevento)
        initShowOut(addcalificacion)
        val tareab = findViewById<FloatingActionButton>(R.id.tareaB)
        val evetob = findViewById<FloatingActionButton>(R.id.eventoB)
        val calificacionb = findViewById<FloatingActionButton>(R.id.calificacionB)
        val anadirb=findViewById<FloatingActionButton>(R.id.botonAñadir)

        anadirb.setOnClickListener{
            toggleFabMode(it)
        }


    }

    private fun initShowOut(v: View){
        v.apply {
            visibility=View.GONE
            translationY=height.toFloat()
            alpha=0f
        }
    }

    private fun toggleFabMode(v:View){
        rotate = rotateFab(v,!rotate)
        if (rotate){
            showIn(addtarea)
            showIn(addevento)
            showIn(addcalificacion)
        }else{
            showOut(addtarea)
            showOut(addevento)
            showOut(addcalificacion)
        }
    }

    private fun showIn(view:View){
        view.apply {
            visibility=View.VISIBLE
            alpha=0f
            translationY=height.toFloat()
            animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object :AnimatorListenerAdapter(){})
                .alpha(1f)
                .start()
        }
    }
    private fun showOut(view: View){
        view.apply {
            visibility=View.VISIBLE
            alpha=0f
            translationY=height.toFloat()
            animate()
                .setDuration(200)
                .translationY(height.toFloat())
                .setListener(object :AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator) {
                        visibility=View.GONE
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(0f)
                .start()
        }

    }

    private fun rotateFab(v: View,rotate:Boolean):Boolean{
        v.animate()
            .setDuration(200)
            .setListener(object :AnimatorListenerAdapter() {})
            .rotation(if (rotate) 180f else 0f)
        return rotate
    }

}
