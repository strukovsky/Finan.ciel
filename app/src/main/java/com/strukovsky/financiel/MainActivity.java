package com.strukovsky.financiel;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.share.AddShareTask;
import com.strukovsky.financiel.db.task.share.ReadAllSharesTask;
import com.strukovsky.financiel.db.task.TaskRunner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_search, R.id.navigation_analysis, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    protected void populateDatabase()
    {
     TaskRunner.INSTANCE.execute(new AddShareTask(this, new Share(6, "ET", "USA", "Energy", "Energy Transfer LP is a company", 1000000)),
                new TaskRunner.Callback<Boolean>() {
                    @Override
                    public void onComplete(Boolean result) {
                        Toast.makeText(MainActivity.this, "added", Toast.LENGTH_LONG).show();
                    }
                });
    }
}

