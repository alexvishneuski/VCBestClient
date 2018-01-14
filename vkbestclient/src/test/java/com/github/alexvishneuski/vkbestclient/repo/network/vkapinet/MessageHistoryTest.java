package com.github.alexvishneuski.vkbestclient.repo.network.vkapinet;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.repo.util.mocks.Mocks;
import com.github.alexvishneuski.vkbestclient.repository.networking.http.IHttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.modelparsing.GsonParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.dialogshistory.VKApiMessagesGetHistoryResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogsHistoryVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogsHistoryVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiMessagesGetHistoryParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = ConstantsUtil.AppConstants.SDK_VERSION
)
public class MessageHistoryTest {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogsHistoryVKApiNetworking member;
    private VKApiUri msgsUri;
    private IHttpClient mHttpClient;

    @Before
    public void setUp() {
        member = new DialogsHistoryVKApiNetworkingImpl();
        msgsUri = getUri();
        mHttpClient = mock(IHttpClient.class);

    }

    @Test
    public void getTotalCount() {
        InputStream mockedInputStream = Mocks.stream("repo/network/VKApiMessagesGetHistoryResult.json");
        when(mHttpClient.requestGet(Matchers.anyString())).thenReturn(mockedInputStream);
        InputStream response = mHttpClient.requestGet("http://myApi/getMessagesInHistory");

        @SuppressWarnings("unchecked") GsonParser<VKApiMessagesGetHistoryResult> parser =
                new GsonParser(response);

        VKApiMessagesGetHistoryResult result =
                parser.parse(VKApiMessagesGetHistoryResult.class);

        int msgCount = result.getResponse().getMessagesCount();

        Assert.assertEquals("", 231, msgCount);
    }

    private VKApiUri getUri() {

        Integer pLimit = 1;
        Integer pUserId = 21327318;

        String limit = String.valueOf(pLimit);
        String userId = String.valueOf(pUserId);

        VKApiMessagesGetHistoryParams msgsParams =
                VKApiMessagesGetHistoryParams.getBuilder().setUserId(userId).setCount(limit).build();
        VKApiUri msgsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetHistory.METHOD_NAME)
                .setParameters(msgsParams)
                .build();

        return msgsUri;
    }
}
