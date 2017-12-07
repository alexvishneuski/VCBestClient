package com.github.alexvishneuski.vkbestclient.presentation.view.activities.study;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.github.alexvishneuski.vkbestclient.presentation.utils.BitmapUtils;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.MessagesTopBarFragment;
import com.github.alexvishneuski.vklayouts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/


public class StudyBasedListViewWithArrayListDialogsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private ListView mMessagesListView;

    /*attribute names for adapter's Map*/
    final String ATTRIBUTE_FRIEND_NAME = "friendName";
    final String ATTRIBUTE_FRIEND_AVATAR = "friendAvatar";
    final String ATTRIBUTE_OWNER_AVATAR = "ownerAvatar";
    final String ATTRIBUTE_DATE = "date";
    final String ATTRIBUTE_MESSAGE_BODY = "messageBody";

    /*
    * resources in drawable
    */
    private int[] mFriendAvatars;
    private int mOwnerAvatar;

    /*
    * resources for adapter
    */
    private String[] mFriendNames;
    private Bitmap[] mRoundFriendAvatars;
    private String[] mDates;
    private String[] mMessageBodies;
    private Bitmap mRoundOwnerAvatar;

    /*source attributes for adapter*/
    private String[] mSourceAttributes;

    /*goals for adapter*/
    private int[] mDestinationViews;

    SimpleAdapter mMessageAdapter;


    private LoadImagesTask loadImagesTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_dialogs_based_list_view);

        /*find top bar container end show there top bar fragment*/
        findTopBarContainer();
        showTopBarFragment();

        /*load round avatars
        * must be executed before creating adapter. To create adapter in onStart or add onMakeRoundAvatars
        * */
        makeAvatarsRound();

        /*
        * creating adapter:
        * find messages list view
        * get resources for adapter: names, Avatars, dates, bodies, ownerAvatar
        * get source attributes for adapter
        * find goals for adapter
        * create adapter
        * set adapter to ListView
        */


        createAdapter();
    }

    private void createAdapter() {

        /*find messages list view*/
        findListMessagesView();

        /*get resources for adapter: names, Avatars, dates, bodies, ownerAvatar*/
        getResourcesForAdapter();

        /*get source attributes for adapter*/
        findSourceAttributes();


        /*find goals for adapter*/
        findDestinationViews();

        /*create adapter*/
        createMessagesAdapter();

        /*set adapter to ListView*/
        setMessageAdapterToListView();
    }

    private void findSourceAttributes() {
        mSourceAttributes = new String[]{
                ATTRIBUTE_FRIEND_AVATAR, ATTRIBUTE_FRIEND_NAME, ATTRIBUTE_DATE, ATTRIBUTE_OWNER_AVATAR, ATTRIBUTE_MESSAGE_BODY};
    }

    /*find goals for adapter*/
    private void findDestinationViews() {
        mDestinationViews = new int[]{
                R.id.contact_user_avatar_image_view, R.id.contact_user_name_text_view,
                R.id.message_sending_date_text_view, R.id.current_user_avatar_image_view, R.id.mesage_body_text_view};
    }

    /*get resources for adapter: names, Avatars, dates, bodies, ownerAvatar*/
    private void getResourcesForAdapter() {
        getFriendNames();
        getFriendAvatars();
        getDates();
        getMessageBodies();
        getOwnerAvatar();

    }


    /*find messages list view*/
    private void findListMessagesView() {
        Log.d(TAG, "findListMessagesView");
        mMessagesListView = (ListView) findViewById(R.id.dialogs_container_list_view);
    }

    /*get resources for adapter: names*/
    private void getFriendNames() {
        Log.d(TAG, "getFriendNames");
        mFriendNames = getResources().getStringArray(R.array.contact_user_full_names);
    }

    /*get resources for adapter: dates*/
    private void getDates() {
        Log.d(TAG, "getDates");
        mDates = getResources().getStringArray(R.array.sending_dates);
    }

    /*get resources for adapter: messageBodies*/
    private void getMessageBodies() {
        Log.d(TAG, "getMessageBodies");
        mMessageBodies = getResources().getStringArray(R.array.message_bodies);
    }

    /*get resources for adapter: FriendAvatars*/
    private void getFriendAvatars() {
        Log.d(TAG, "getFriendAvatars");
        mFriendAvatars = new int[]{
                R.drawable._jonny_dep, R.drawable._al_pacino, R.drawable._robert_de_niro,
                R.drawable._kevin_spacey, R.drawable._denzel_washington, R.drawable._russel_crowe,
                R.drawable._brad_pitt, R.drawable._angelina_jolie, R.drawable._leonardo_dicaprio,
                R.drawable._tom_cruise, R.drawable._john_travolta, R.drawable._arnold_schwarzenegger};
        mRoundFriendAvatars = new Bitmap[mFriendAvatars.length];
    }

    /*get resources for adapter: ownerAvatar*/
    private void getOwnerAvatar() {
        Log.d(TAG, "getOwnerAvatar");
        mOwnerAvatar = R.drawable._matt_damon_ovner;
    }


    /*create adapter*/
    private void createMessagesAdapter() {

        /*wrap data into for adapter understandable structure  */
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                mFriendNames.length);
        Map<String, Object> m;
        for (int i = 0; i < mFriendNames.length; i++) {
            m = new HashMap<String, Object>();
            /*FIXME with
            m.put(ATTRIBUTE_FRIEND_AVATAR, mRoundFriendAvatars[i])
            doesn't displayed;*/
            m.put(ATTRIBUTE_FRIEND_AVATAR, mFriendAvatars[i]);
            m.put(ATTRIBUTE_FRIEND_NAME, mFriendNames[i]);
            m.put(ATTRIBUTE_DATE, mDates[i]);
            m.put(ATTRIBUTE_OWNER_AVATAR, mRoundOwnerAvatar);
            m.put(ATTRIBUTE_MESSAGE_BODY, mMessageBodies[i]);

            data.add(m);


        }

        Log.d(TAG, "createSimpleMessagesListViewAdapter");
        mMessageAdapter = new SimpleAdapter(this, data, R.layout.view_message_in_dialog_list_item_incoming_read, mSourceAttributes, mDestinationViews);
    }

    /*set adapter to message list view*/
    private void setMessageAdapterToListView() {
        Log.d(TAG, "setMessageAdapterToListView");
        mMessagesListView.setAdapter(mMessageAdapter);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

        /*makeAvatarsRound();*/
        /*createAdapter();*/


    }

    private void makeAvatarsRound() {
        Log.d(TAG, "makeAvatarsRound");
        /*get images to array */
        getFriendAvatars();
        /*start task*/
        loadImagesTask = new LoadImagesTask();
        loadImagesTask.execute();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        //   loadImagesTask.cancel(true);

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

    private class LoadImagesTask extends AsyncTask<Void, Void, List<Bitmap>> {


        @Override
        protected List<Bitmap> doInBackground(Void... params) {
            List<Bitmap> avatarBitmaps = new ArrayList<>();
            /*for friendAvatars*/
            for (int i = 0; i < mFriendAvatars.length; i++) {
                avatarBitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(StudyBasedListViewWithArrayListDialogsActivity.this.getResources(), mFriendAvatars[i]), 25));
            }
            /*for ownerAvatar*/
            avatarBitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(StudyBasedListViewWithArrayListDialogsActivity.this.getResources(), mOwnerAvatar), 25));

            return avatarBitmaps;

        }

        @Override
        protected void onPostExecute(List<Bitmap> result) {
            if (isCancelled() || result == null) {
                return;
            }
            /*extract friendsAvatars*/
            for (int i = 0; i < mRoundFriendAvatars.length; i++) {
                mRoundFriendAvatars[i] = result.get(i);
            }
            /*extract ownerAvatar*/
            mRoundOwnerAvatar = result.get(mRoundFriendAvatars.length);

            /*imageOwner.setImageBitmap(result.get(0));
            image1.setImageBitmap(result.get(1));*/

        }

    }
}
