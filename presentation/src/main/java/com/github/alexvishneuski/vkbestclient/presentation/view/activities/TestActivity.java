package com.github.alexvishneuski.vkbestclient.presentation.view.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.alexvishneuski.vklayouts.R;

public class TestActivity extends AppCompatActivity {

    String[] names = {"Johnny Depp", "Al Pacino", "Robert De Niro", "Kevin Spacey", "Denzel Washington", "Russell Crowe",
            "Brad Pitt", "Angelina Jolie", "Leonardo DiCaprio", "Tom Cruise", "John Travolta", "Arnold Schwarzenegger"};

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        /*find list view*/
        ListView lvMain = (ListView) findViewById(R.id.test_list);

        /*create adapter*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names);

        /*set adapter to list*/
        lvMain.setAdapter(adapter);

    }
}
