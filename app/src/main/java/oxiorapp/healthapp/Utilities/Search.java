package oxiorapp.healthapp.Utilities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import oxiorapp.healthapp.Adapters.FoodInfoAdapter;
import oxiorapp.healthapp.Database.DatabaseAccess;
import oxiorapp.healthapp.R;

/**
 * Created by BSIT on 3/23/2016.
 */
public class Search extends AppCompatActivity implements View.OnClickListener{
    ListView mListView;
    EditText mFoodName;
    Button mClickSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        init();
        FoodList();
    }

    public void showDatabaseAccess(){
        Resources res = getResources();
        Resources res1 = getResources();
        String result = "Not Available in the Database";
        String getFood= mFoodName.getText().toString();
        String foodList [] = res.getStringArray(R.array.FoodName);
        String typeACompat [] = res1.getStringArray(R.array.Type_A);

        if(Arrays.asList(foodList).contains(getFood)) {
            int i = Arrays.asList(foodList).indexOf(getFood);
            result = typeACompat[i];
        }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();


    }

    public void init(){
        mFoodName = (EditText) findViewById(R.id.etFoodName);
        mClickSearch = (Button) findViewById(R.id.btnSearch);
        mListView = (ListView) findViewById(R.id.listView);

    }

    @Override
    public void onClick(View v) {
        showDatabaseAccess();
    }

    public void FoodList(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        FoodInfoAdapter foodInfoAdapter = new FoodInfoAdapter(this, databaseAccess.getFood());
        databaseAccess.close();
        mListView.setAdapter(foodInfoAdapter);
    }






}



