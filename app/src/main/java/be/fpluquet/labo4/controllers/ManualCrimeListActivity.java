package be.fpluquet.labo4.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.fpluquet.labo4.R;
import be.fpluquet.labo4.models.Crime;
import be.fpluquet.labo4.models.CrimeLab;

public class ManualCrimeListActivity extends AppCompatActivity {

    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_crime_list);

        mContainer = (LinearLayout) this.findViewById(R.id.crime_list);
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        CrimeLab lab = CrimeLab.get(this);
        for(final Crime crime : lab.getCrimes()) {
            View crimeView = getCrimeView(crime);
            mContainer.addView(crimeView);
        }
    }

    private View getCrimeView(final Crime crime) {
//        // création d'un LinearLayout vertical avec padding de 8
//        LinearLayout columnForCrime = new LinearLayout(getApplicationContext());
//        columnForCrime.setOrientation(LinearLayout.VERTICAL);
//        columnForCrime.setPadding(8, 8, 8, 8);
//
//        // création des TextViews
//        TextView titleView = getTextView(crime.getTitle());
//        TextView dateView = getTextView(crime.getDate().toString());
//
//        // ajout des TextViews dans le LinearLayout
//        columnForCrime.addView(titleView);
//        columnForCrime.addView(dateView);
//        setClickOnCrimeView(crime, columnForCrime);
//        return columnForCrime;

        View columnForCrime = getLayoutInflater().inflate(R.layout.list_item_crime, null);
        ((TextView)columnForCrime.findViewById(R.id.crime_title)).setText(crime.getTitle());
        ((TextView)columnForCrime.findViewById(R.id.crime_date)).setText(crime.getDate().toString());
        setClickOnCrimeView(crime, columnForCrime);
        return columnForCrime;
    }

    private void setClickOnCrimeView(Crime crime, View columnForCrime) {
        columnForCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ManualCrimeListActivity.this, CrimeActivity.class);
                intent.putExtra(CrimeFragment.CRIME_ID, crime.getId());
                startActivity(intent);
            }
        });
    }

//    /*
//     * Renvoie un TextView avec le texte text
//     */
//    private TextView getTextView(String text) {
//        TextView textView = new TextView(getApplicationContext());
//        textView.setText(text);
//        textView.setLayoutParams(new FrameLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        return textView;
//    }
}