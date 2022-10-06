package leegyung.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    Integer[] Array = new Integer[] {0,1,2,3,4,5,6,7,8,9};
    View Puzzle;
    Button Solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();

        Spinner spinner = findViewById(R.id.NumberSelec);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Sudoku", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Number", Array[position]);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Sudoku", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Number", 0);
                editor.commit();
            }
        });

        Puzzle = (View) findViewById(R.id.Puzzle);
        Solve = (Button) findViewById(R.id.SolveBtn);
        Solve.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Sudoku", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Solve", 100);
                editor.commit();
                Puzzle.invalidate();
            }
        });



    }

}