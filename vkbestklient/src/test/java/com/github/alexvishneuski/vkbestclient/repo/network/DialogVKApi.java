package com.github.alexvishneuski.vkbestclient.repo.network;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = ConstantsUtil.AppConstants.SDK_VERSION
)
public class DialogVKApi {

    /*public final String TAG = this.getClass().getSimpleName();

    private VKApiMessagesGetDialogsResponse mResponse;

    private ArrayList<VKApiDialog> mDialogs;
    private IDialogVKApiNetworking mDialogProvider;



    //classMember initialization
    private IHttpClient mHttpClient;


    private int mDialogCount;

    @Before
    public void setUp() {
        mDialogProvider = new DialogVKApiNetworkingImpl();
        mDialogs = new ArrayList<>();
    }

    @Test
    public void getDialogListTest() {
        Log.d(TAG, "testDialog called ");

        mDialogCount = mDialogProvider.getDialogList().getDialogCount();
        Log.d(TAG, "recd dialogs items = " + mDialogCount);

        mDialogs.addAll(mDialogProvider.getDialogList().getDialogs());

        Log.d(TAG, "dialogs are recd");
        for (VKApiDialog dialog : mDialogs
                ) {
            System.out.println(dialog.toString());
        }

    }
*/



    /*@Before
    public void setUp() {
        mHttpClient = mock(HttpClient.class);
        mDialogs = new ArrayList<>();

        // mListener = mock(DialogVKApiNetworkingImpl.MyResponseListener.class);
        mListener = new DialogVKApiNetworkingImpl.MyResponseListener();
    }*/
    /*
    @Test
    public void testDialog() {
        Log.d(TAG, "testDialog called ");

        //prepared response with jsonObject

        mDialogCount = mDialogProvider.getDialogs().getDialogCount();
        Log.d(TAG, "recd dialogs items = " + mDialogCount);




        InputStream mockedInputStream = Mocks.stream("VKApiMessagesGetDialogsResult.json");

        doReturn(mockedInputStream),when(mHttpClient.requestGet(null,null));
        when(mHttpClient.requestGet(Matchers.anyString(), mListener)).thenReturn(mockedInputStream);
        //InputStream response = mHttpClient.requestGet("http://myBackEnd/getInvoice?id=29", mListener);


        mDialogs.addAll(mDialogProvider.getDialogs().getDialogs());

        //  Log.d(TAG, "dialogs are recd");
        for (VKApiDialog dialog : mDialogs
                ) {
            System.out.println(dialog.toString());
        }
    }
    */
    /*
    private ListRegisteredUsers readRegisteredUsersList() {
        InputStream mockedInputStream = Mocks.stream("generated.json");
        Reader reader = new InputStreamReader(mockedInputStream);

        return new Gson().fromJson(reader, ListRegisteredUsers.class);
    }
    */
}
