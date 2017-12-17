package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDialogsFragment extends Fragment {

    /*id of container in activity*/
    private int mDialogsLayoutId;

    /*view of this fragment*/
    private View mView;


    public static final String TEST_VIEW_URL = "https://pp.userapi.com/c627921/v627921671/289ec/CTenEfmZ2Rw.jpg";
    public static final String TEST_CONTACT_USER_NAME = "Contact user full name %s";
    public static final String TEST_MESSAGE_BODY = getMesageBody() + "%s";
    public static final String TEST_MESSAGE_SENDING_DATE = "%s.%s";

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private List<MessageInDialogListViewModel> mMessageList;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        createRecyclerView(mView);
        setLayoutManagerToRecyclerView();
        loadDataToMessageInDialogList();
        createAdapter();
        setAdapterToView();

        return mView;
    }

    private void initView(LayoutInflater inflater) {
    /*find id*/
        mDialogsLayoutId = R.layout.fragment_recycler_view;
        /*create view*/
        mView = inflater.inflate(mDialogsLayoutId, null);
    }

    /*create recycler view*/
    private void createRecyclerView(View pView) {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = pView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
    }

    private void setLayoutManagerToRecyclerView() {
        Log.d(TAG, "setLayoutManagerToRecyclerView called");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void loadDataToMessageInDialogList() {
        Log.d(TAG, "loadDataToMessageInDialogList called");
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


    private void loadDataToMessageInDialogList(String pRealDataTAG) {
        Log.d(TAG, "loadDataToMessageInDialogList called");
        mMessageList = new ArrayList<>();

        /*
        UIModel
       ======================
        private Long mId;
        private UserInDialogListViewModel mCurrentUser;
        private UserInDialogListViewModel mContactUser;
        private MessageDirectionViewModel mMessageDirection;
        private String mMessageSendingDate;
        private String mMessageTitle;
        private String mMessageBody;
        private boolean mIsMessageRead;

        steps in presentation
       =======================
        1. get messagesInDialogList from interactor

        2. convert from datamodel to UI model

        3. return UI MOdel
        */


       /*

        steps in interactor
       =======================

       1. get data from Repository
            AsyncTaks:
            1.1 AT for currentUser(name, avatar)
            getCurrentUser().getAvatarUrl()

            1.2. AT get messagesInDialog

            1.3. AT for each contactUser(name, avatar)
            getConactUSer();

       2. build datamodel

       3. return datamodel


       steps in repository
       =======================

        1. executing interactor's requests

        2. convert every result to datamodel
       */

    }


    /*create adapter and send him messageList*/
    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mAdapter = new MessageInDialogListRecyclerAdapter(mMessageList);
    }

    private void setAdapterToView() {
        Log.d(TAG, "setAdapterToView called ");
        mRecyclerView.setAdapter(mAdapter);
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