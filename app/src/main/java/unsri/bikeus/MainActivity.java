package unsri.bikeus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import unsri.bikeus.databinding.ActivityMainBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SqliteHelper sqliteHelper = new SqliteHelper(this);
        if(sqliteHelper.isBikeExistInEmail(User.getEmail()) == true){
            replaceFragment(new BicycleFragment());
        } else{
            replaceFragment(new AddBicycleFragment());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bicycle:
                    if(sqliteHelper.isBikeExistInEmail(User.getEmail()) == true){
                        replaceFragment(new BicycleFragment());
                    } else{
                        replaceFragment(new AddBicycleFragment());
                    }
                    break;
                case R.id.account:
                    replaceFragment(new AccountFragment());
                    break;
                case R.id.history:
                    replaceFragment(new HistoryFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}