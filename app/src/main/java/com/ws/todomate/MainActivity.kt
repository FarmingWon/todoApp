package com.ws.todomate

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.calendar.AdapterMonth
import com.ws.todomate.databinding.ActivityMainBinding
import com.ws.todomate.drawer_header.AfterJoinActivity
import com.ws.todomate.drawer_header.EmailActivity
import com.ws.todomate.drawer_header.SettingActivity
import com.ws.todomate.login.loginActivity
import com.ws.todomate.todoList.AdapterTodo
import org.w3c.dom.Text
import java.lang.Exception

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //drawer
        setSupportActionBar(findViewById(R.id.toolbar)) // 툴바 적용

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.navi_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<ImageView>(R.id.settingBtn).setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val headerJoin = headerView.findViewById<TextView>(R.id.appbar_join)
        headerJoin.setOnClickListener {
            if(Anonymous.anays == true){
                val intent = Intent(this, AfterJoinActivity::class.java)
                startActivity(intent)
            }
        }
        if(Anonymous.anays == false) {
            headerJoin.setText(Anonymous.getEmail())
        }

        //custom Calendar
        val monthListManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val monthListAdapter = AdapterMonth()
        val calendar_custom = findViewById<RecyclerView>(R.id.calendar_custom)
        calendar_custom.apply {
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(calendar_custom)

        val todolist = mutableListOf<String>()
        todolist.add("exex1")
        todolist.add("ex2")
        todolist.add("ex3")


        var visible : Boolean = false
        binding.plusBtn.setOnClickListener {
            if(visible == false){
                visible = true
            }else {
                visible = false
            }
            binding.contentArea.isVisible = visible
        }

        val todoAdapter = AdapterTodo(todolist)
        val LVAdapter = findViewById<ListView>(R.id.todo_listview)
        LVAdapter.adapter = todoAdapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1-> Toast.makeText(this,"menu_item1 실행",Toast.LENGTH_SHORT).show()
            R.id.menu2-> Toast.makeText(this,"menu_item2 실행",Toast.LENGTH_SHORT).show()
            R.id.menu3-> Toast.makeText(this,"menu_item3 실행",Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                // 햄버거 버튼 클릭시 네비게이션 드로어 열기
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers()
        } else{
            super.onBackPressed()
        }
    }

}