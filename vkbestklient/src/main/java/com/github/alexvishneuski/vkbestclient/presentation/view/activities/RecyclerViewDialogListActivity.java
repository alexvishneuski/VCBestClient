package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.MessagesTopBarFragment;

import java.util.ArrayList;
import java.util.List;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/

public class RecyclerViewDialogListActivity extends AppCompatActivity {

    public static final String TEST_VIEW_URL = "https://pp.userapi.com/c627921/v627921671/289ec/CTenEfmZ2Rw.jpg";
    public static final String TEST_CONTACT_USER_NAME = "Contact user full name %s";
    public static final String TEST_MESSAGE_BODY = getMesageBody() + "%s";
    public static final String TEST_MESSAGE_SENDING_DATE = "%s.%s";

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private List<MessageInDialogListViewModel> mMessageList;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_recycler_view_dialogs);
        initFragments();

        createRecyclerViewAndSetLayoutManager();
        loadDataToMessageList();
        createAdapter();

        /*set adapter to view*/
        mRecyclerView.setAdapter(mAdapter);
    }

    /*create recycler view set linearLayoutManager to him*/
    private void createRecyclerViewAndSetLayoutManager() {
        Log.d(TAG, "createRecyclerViewAndSetLayoutManager");
        mRecyclerView = findViewById(R.id.dialogs_container_recycler_view);

        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /*create adapter and send him messageList*/
    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mAdapter = new MessageInDialogListRecyclerAdapter(mMessageList);
    }

    /*find top bar container end show there top bar fragment*/
    private void initFragments() {
        Log.d(TAG, "initFragments");
        findTopBarContainer();
        showTopBarFragment();
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

    private void loadDataToMessageList() {
        Log.d(TAG, "loadDataToMessageList");
        mMessageList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mMessageList.add(new MessageInDialogListViewModel(
                    new UserInDialogListViewModel(
                            String.format(TEST_CONTACT_USER_NAME, i), TEST_VIEW_URL),
                    String.format(TEST_MESSAGE_SENDING_DATE, i, i),
                    String.format(TEST_MESSAGE_BODY, i),
                    (i % 2 == 0) ? MessageDirectionViewModel.INCOMING : MessageDirectionViewModel.OUTGOING,
                    (i % 2 == 0) ? true : false));
        }
    }

    @NonNull
    private static String getMesageBody() {
        return "To be, or not to be: that is the question:\n" +
                "Whether 'tis nobler in the mind to suffer\n" +
                "The slings and arrows of outrageous fortune,\n" +
                "Or to take arms against a sea of troubles,\n" +
                "And by opposing end them? To die: to sleep;\n" +
                "No more; and by a sleep to say we end\n" +
                "The heart-ache and the thousand natural shocks\n" +
                "That flesh is heir to, 'tis a consummation\n" +
                "Devoutly to be wish'd. To die, to sleep;\n" +
                "To sleep: perchance to dream: ay, there's the rub;\n" +
                "For in that sleep of death what dreams may come\n" +
                "When we have shuffled off this mortal coil,\n" +
                "Must give us pause: there's the respect\n" +
                "That makes calamity of so long life;\n" +
                "For who would bear the whips and scorns of time,\n" +
                "The oppressor's wrong, the proud man's contumely,\n" +
                "The pangs of despised love, the law's delay,\n" +
                "The insolence of office and the spurns\n" +
                "That patient merit of the unworthy takes,\n" +
                "When he himself might his quietus make\n" +
                "With a bare bodkin? who would fardels bear,\n" +
                "To grunt and sweat under a weary life,\n" +
                "But that the dread of something after death,\n" +
                "The undiscover'd country from whose bourn\n" +
                "No traveller returns, puzzles the will\n" +
                "And makes us rather bear those ills we have\n" +
                "Than fly to others that we know not of?\n" +
                "Thus conscience does make cowards of us all;\n" +
                "And thus the native hue of resolution\n" +
                "Is sicklied o'er with the pale cast of thought,\n" +
                "And enterprises of great pith and moment\n" +
                "With this regard their currents turn awry,\n" +
                "And lose the name of action. - Soft you now!\n" +
                "The fair Ophelia! Nymph, in thy orisons\n" +
                "Be all my sins remember'd. %";
    }
}
