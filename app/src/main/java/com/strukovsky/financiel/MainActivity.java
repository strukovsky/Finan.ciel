package com.strukovsky.financiel;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.strukovsky.financiel.db.entity.BalanceSheet;
import com.strukovsky.financiel.db.entity.CashFlow;
import com.strukovsky.financiel.db.entity.Share;
import com.strukovsky.financiel.db.task.balance_sheet.AddAllBalanceSheetTask;
import com.strukovsky.financiel.db.task.cash_flow.AddAllCashFlowTask;
import com.strukovsky.financiel.db.task.share.AddAllSharesTask;
import com.strukovsky.financiel.db.task.TaskRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

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
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        populateDatabase();

    }

    public NavController getNavController() {
        return navController;
    }

    protected void populateDatabase() {
        List<Share> data = Arrays.asList(
                new Share(1, "APPL", "Apple", "USA", "IT",
                        "Apple Company is big company", 16487121),
                new Share(2, "ET", "Energy Transfer LP", "USA", "Energetics",
                        "Energy Transfer LP is gas company", 10000000),
                new Share(3, "F", "Ford", "USA", "Automobiles",
                        "Ford is automobiles", 5000000)
        );
        List<BalanceSheet> balanceSheets = Arrays.asList(
                new BalanceSheet(1, 1, 351002, 134836, 34940,
                        27699, 26278, 127877,
                        39440,

                        287912, 125481, 54763, 7612, -1, 109106,
                        63090)
        );
        List<CashFlow> cashFlows = Arrays.asList(
                new CashFlow(
                        1, 1, 104038, 94680, 14545, 93353
                )
        );
        TaskRunner.INSTANCE.execute(new AddAllSharesTask(this, data), new TaskRunner.Callback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {

            }
        });
        TaskRunner.INSTANCE.execute(new AddAllBalanceSheetTask(this, balanceSheets), new TaskRunner.Callback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {

            }
        });
        TaskRunner.INSTANCE.execute(new AddAllCashFlowTask(this, cashFlows), new TaskRunner.Callback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {

            }
        });

    }
}

