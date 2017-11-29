package com.github.alexvishneuski.vkbestclient.presentation.view.activities.study;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.github.alexvishneuski.vkbestclient.presentation.utils.StubResourcesUtility;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.study.StudyDialogsListViewAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.MessagesTopBarFragment;
import com.github.alexvishneuski.vklayouts.R;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/


public class StudyBasedListViewWithBaseAdapterDialogsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private ListView listView;
    StubResourcesUtility resourcesStub;

    StudyDialogsListViewAdapter lastMessageAdapter;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_dialogs_based_list_view);

        /*find top bar container end show there top bar fragment*/
        findTopBarContainer();
        showTopBarFragment();

        /*find list view*/
        listView = (ListView) findViewById(R.id.dialogs_container_list_view);

        /*creatingResourcesStub*/
        resourcesStub = StubResourcesUtility.getStubResourcesUtility(getApplicationContext());

        /*create adapter*/
        lastMessageAdapter = new StudyDialogsListViewAdapter(this, resourcesStub.getLastMessages());

        /*set adapter to list*/
        listView.setAdapter(lastMessageAdapter);

    }


        /*show any fragment on this activity*/

    public void showFragment(int frameContainer, Fragment fragment) {
        Log.d(TAG, "showFragment");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    /*find top bar container for top bar fragment*/
    private void findTopBarContainer() {
        Log.d(TAG, "findTopBarContainer");
        mTopBarFrameContainer = R.id.top_bar_frame_container;

    }

    /*show top bar fragment in top bar container*/
    private void showTopBarFragment() {
        Log.d(TAG, "showTopBarFragment");
        showFragment(mTopBarFrameContainer, new MessagesTopBarFragment());

    }

}
