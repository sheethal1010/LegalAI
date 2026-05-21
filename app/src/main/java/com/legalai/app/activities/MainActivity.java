package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.legalai.app.R;
import com.legalai.app.fragments.AgentsFragment;
import com.legalai.app.fragments.CasesFragment;
import com.legalai.app.fragments.LawyersFragment;
import com.legalai.app.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private FloatingActionButton fabChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        fabChat = findViewById(R.id.fab_chat);

        // Load default fragment
        loadFragment(new LawyersFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_lawyers) { loadFragment(new LawyersFragment()); return true; }
            if (id == R.id.nav_agents) { loadFragment(new AgentsFragment()); return true; }
            if (id == R.id.nav_cases) { loadFragment(new CasesFragment()); return true; }
            if (id == R.id.nav_profile) { loadFragment(new ProfileFragment()); return true; }
            return false;
        });

        fabChat.setOnClickListener(v ->
                startActivity(new Intent(this, ChatActivity.class)));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
