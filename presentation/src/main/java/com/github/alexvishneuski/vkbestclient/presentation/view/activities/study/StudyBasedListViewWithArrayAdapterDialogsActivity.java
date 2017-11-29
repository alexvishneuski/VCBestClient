package com.github.alexvishneuski.vkbestclient.presentation.view.activities.study;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.alexvishneuski.vkbestclient.presentation.utils.StubResourcesUtility;
import com.github.alexvishneuski.vklayouts.R;

public class StudyBasedListViewWithArrayAdapterDialogsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private ListView basedArrayAdapterListView;
    StubResourcesUtility resourcesStub;
    ArrayAdapter<String> messagesAdapter;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_based_list_view_with_array_adapter_study);
        resourcesStub = StubResourcesUtility.getStubResourcesUtility(getApplicationContext());

        /*find list view*/
        basedArrayAdapterListView = (ListView) findViewById(R.id.test_list);

        /*create adapter*/
        messagesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, resourcesStub.getContactUserFullNames());

        /*set adapter to list*/
        basedArrayAdapterListView.setAdapter(messagesAdapter);

    }
}
