package com.voidhash.thecatapp.ui.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fragment_content, MainFragment())
        ft.commit()

        /* Substitui um Fragment
        ft.replace(R.id.fragment_content, new MainFragment());
        ft.commit()
        */

        /* Remove um Fragment
        val fragment: Fragment = fm.findFragmentById(R.id.fragment_content);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
        */
    }
}