package be.fpluquet.labo4.controllers;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import be.fpluquet.labo4.models.Crime;
import be.fpluquet.labo4.models.CrimeLab;

public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Ajouter dix crimes aléatoires");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0) {
            for(int i = 0; i < 10; i++) {
                CrimeLab.get(getApplicationContext()).addCrime(new Crime());
            }
            ((CrimeListFragment)this.fragment).updateUI();
        }
        return super.onOptionsItemSelected(item);
    }
}
