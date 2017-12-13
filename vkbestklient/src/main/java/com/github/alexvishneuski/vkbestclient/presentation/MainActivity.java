package com.github.alexvishneuski.vkbestclient.presentation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.datamodel.DomainTest;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.InteractorTest;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.RecyclerViewDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.study.StudyBasedListViewWithArrayAdapterDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.study.StudyBasedListViewWithArrayListDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.study.StudyBasedListViewWithBaseAdapterDialogsActivity;
import com.github.alexvishneuski.vkbestclient.repository.RepositoryTest;
import com.github.alexvishneuski.vkbestclient.repository.database.SqlConnector;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessagesTableModel;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UsersTableModel;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private Button mToDialogsBasedListViewWithArrayListButton;

    private Button mToDialogsBasedListViewWithBaseAdapterAndViewHolderButton;

    private Button mToDialogsBasedListViewWithArrayAdapterButton;

    private Button mToDialogsBasedRecyclerViewButton;

    private static IDialogInteractor mDialogInteractor;

    private GetDialogListAsStringAsyncTasc mGetDialogAsStringAsyncTasc;

    GetDialogListAsyncTasc mGetDialogListAsyncTasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        /*creating buttons*/
        initButtons();

        checkOutsideTiersAccess();

        /*getting dialog List as String*/
        //  executeGetDialogListAsStringAsyncTasc();

        /*getting dialog List*/
        executeGetDialogListAsyncTasc();

        /*FROM*/
        /*<--------------------------->*/

        /*!!CREATING USERS!*/
        SqlConnector mSqlConnector = new SqlConnector(this);

        UserDbModel[] userArray = prepareUsersForInsertIntoDb();
        assertEquals(3, userArray.length);

        MessageDbModel[] messagesArray = prepareMessagesForInsertIntoDb();
        assertEquals(3, messagesArray.length);

        SQLiteDatabase writeConnection = mSqlConnector.getWritableDatabase();
        writeConnection.beginTransaction();

        for (UserDbModel user : userArray) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(UsersTableModel.FIRST_NAME, user.getFirstName());
            contentValues.put(UsersTableModel.LAST_NAME, user.getLastName());
            contentValues.put(UsersTableModel.AVATAR_PATH, user.getAvatarPath());

            //TODO read about nullColumnHack
            //TODO read about conflicts
            long id = writeConnection.insert(UsersTableModel.TABLE_NAME, null, contentValues);
            writeConnection.getPageSize();
        }

        for (MessageDbModel m : messagesArray) {
            ContentValues messageContentValues = new ContentValues();
            messageContentValues.put(MessagesTableModel.AUTHOR_ID, m.getAuthor_id());
            messageContentValues.put(MessagesTableModel.RECIPIENT_ID, m.getRecipient_id());
            messageContentValues.put(MessagesTableModel.TITLE, m.getMessageTitle());
            messageContentValues.put(MessagesTableModel.BODY, m.getMessageBody());
            messageContentValues.put(MessagesTableModel.IS_READ, !m.isMessageRead() ? 0 : 1);
        }

        writeConnection.setTransactionSuccessful();
        writeConnection.endTransaction();

        /*!!CREATING MESSAGES!*/
        /*TO*/
        /*<--------------------------->*/

    }


    /*FROM*/
    /*<--------------------------->*/
    private UserDbModel[] prepareUsersForInsertIntoDb() {
        //TODO fill data
        return new UserDbModel[]{new UserDbModel("1", "2", "3"), new UserDbModel("1", "2", "3"), new UserDbModel("1", "2", "3")};
    }

    private MessageDbModel[] prepareMessagesForInsertIntoDb() {
        //TODO fill data
        return new MessageDbModel[]{new MessageDbModel(1, 2, 3, "m1", "m2", true), new MessageDbModel(2, 3, 3, "m1", "m2", true), new MessageDbModel(4, 5, 3, "m1", "m2", true)};
    }
    /*TO*/
    /*<--------------------------->*/


    private void executeGetDialogListAsyncTasc() {
        Log.d(TAG, "executeGetDialogListAsyncTasc: called");
        mGetDialogListAsyncTasc = new GetDialogListAsyncTasc();
        mGetDialogListAsyncTasc.execute();
    }

    private void executeGetDialogListAsStringAsyncTasc() {
        Log.d(TAG, "executeGetDialogListAsStringAsyncTasc: called");
        mGetDialogAsStringAsyncTasc = new GetDialogListAsStringAsyncTasc();
        mGetDialogAsStringAsyncTasc.execute();
    }

    private static class GetDialogListAsStringAsyncTasc extends AsyncTask<Void, Void, String> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAsStringAT";

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            mDialogInteractor = new DialogInteractorImpl();
            String result = mDialogInteractor.getResultAsString();

            Log.d(ASYNC_TASK_TAG, "doInBackground: start result print");
            System.out.println(result);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish result print");

            return result;
        }
    }

    private static class GetDialogListAsyncTasc extends AsyncTask<Void, Void, List<VKApiDialog>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAsStringAT";

        @Override
        protected List<VKApiDialog> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<VKApiDialog> dialogs = new ArrayList<>();

            mDialogInteractor = new DialogInteractorImpl();
            dialogs.addAll(mDialogInteractor.getDialogList());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start dialogList print");
            System.out.println(dialogs);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish dialogList print");

            return dialogs;
        }
    }

    private void initButtons() {
        Log.d(TAG, "initButtons");
        mToDialogsBasedListViewWithArrayListButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_array_list_as_adapter_button);
        initToDialogsBasedListViewWithArrayListButton();

        mToDialogsBasedListViewWithBaseAdapterAndViewHolderButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_base_adapter_and_view_holder_button);
        initToDialogsBasedListViewWithBaseAdapterAndViewHolderButton();

        /*TODO delete after testing*/
        mToDialogsBasedListViewWithArrayAdapterButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_array_adapter_button);
        initToDialogsBasedListViewWithArrayAdapterButton();

        mToDialogsBasedRecyclerViewButton = findViewById(R.id.to_dialogs_activity_based_recycler_view_button);
        initToDialogsBasedRecyclerViewButton();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();

    }

    private void checkOutsideTiersAccess() {
        Log.d(TAG, "checkOutsideTiersAccess");

        InteractorTest interactorTest = new InteractorTest();
        interactorTest.getTestMessage();

        DomainTest domainTest = new DomainTest();
        domainTest.getTestMessage();

        RepositoryTest repoTest = new RepositoryTest();
        repoTest.getTestMessage();
    }

    private void initToDialogsBasedListViewWithArrayListButton() {
        Log.d(TAG, "initToDialogsBasedListViewWithArrayListButton");
        mToDialogsBasedListViewWithArrayListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithArrayListDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void initToDialogsBasedListViewWithBaseAdapterAndViewHolderButton() {
        Log.d(TAG, "initToDialogsBasedListViewWithBaseAdapterAndViewHolderButton");
        mToDialogsBasedListViewWithBaseAdapterAndViewHolderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithBaseAdapterDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    /*TODO delete after testing*/
    private void initToDialogsBasedListViewWithArrayAdapterButton() {
        Log.d(TAG, "initToDialogsBasedListViewWithArrayAdapterButton");
        mToDialogsBasedListViewWithArrayAdapterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithArrayAdapterDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void initToDialogsBasedRecyclerViewButton() {
        Log.d(TAG, "initToDialogsBasedRecyclerViewButton");
        mToDialogsBasedRecyclerViewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

}
