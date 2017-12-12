package presentation.utils;

import android.content.Context;

import presentation.model.MessageDirectionViewModel;
import presentation.model.study.StudyMessageViewModelStub;
import com.github.alexvishneuski.vklayouts.R;

import java.util.ArrayList;
import java.util.List;


public class StubResourcesUtility {


    public final String TAG = this.getClass().getSimpleName();

    private String[] contactUserFullNames;
    private String currentUserFullName;
    private String[] sendingDates;
    private String[] messageBodies;
    private MessageDirectionViewModel[] directions;
    private Boolean[] isReadArray;

    /*
      * resources in drawable format
      */
    private int[] contactAvatarIdArray;


    private Context context;

    private List<StudyMessageViewModelStub> lastMessages;

    private static StubResourcesUtility stubResourcesUtility;

    private final String CURRENT_USER_NAME = "Aliaksandr Vishneuski";

    public StubResourcesUtility(Context context) {
        this.context = context;
        contactUserFullNames = context.getResources().getStringArray(R.array.contact_user_full_names);
        currentUserFullName = CURRENT_USER_NAME;
        sendingDates = context.getResources().getStringArray(R.array.sending_dates);
        messageBodies = context.getResources().getStringArray(R.array.message_bodies);
        contactAvatarIdArray = createContactAvatarIdArray();
        isReadArray = createIsReadArray();
        directions = createMessageDirectionArray();

        lastMessages = new ArrayList<>();

        for (int i = 0; i < contactUserFullNames.length; i++) {
            lastMessages.add(new StudyMessageViewModelStub(contactUserFullNames[i], currentUserFullName, directions[i], sendingDates[i], messageBodies[i], isReadArray[i]));

        }
    }


    public static StubResourcesUtility getStubResourcesUtility(Context context) {
        if (stubResourcesUtility == null) {
            stubResourcesUtility = new StubResourcesUtility(context);
        }
        return stubResourcesUtility;
    }

    public List<StudyMessageViewModelStub> getLastMessages() {
        return lastMessages;
    }

    public String[] getContactUserFullNames() {
        return contactUserFullNames;
    }

    public String getCurrentUserFullName() {
        return currentUserFullName;
    }

    public String[] getSendingDates() {
        return sendingDates;
    }

    public String[] getMessageBodies() {
        return messageBodies;
    }

    public MessageDirectionViewModel[] getDirections() {
        return directions;
    }

    public Boolean[] getIsReadArray() {
        return isReadArray;
    }

    public int[] getContactAvatarIdArray() {
        return contactAvatarIdArray;
    }

    private int[] createContactAvatarIdArray() {
        int[] intIdArray = new int[]{
                R.drawable._jonny_dep, R.drawable._al_pacino, R.drawable._robert_de_niro,
                R.drawable._kevin_spacey, R.drawable._denzel_washington, R.drawable._russel_crowe,
                R.drawable._brad_pitt, R.drawable._angelina_jolie, R.drawable._leonardo_dicaprio,
                R.drawable._tom_cruise, R.drawable._john_travolta, R.drawable._arnold_schwarzenegger};
        return intIdArray;
    }

    private Boolean[] createIsReadArray() {
        Boolean[] booleanIsReadArray = new Boolean[12];
        for (int i = 0; i < booleanIsReadArray.length; i++) {

            booleanIsReadArray[i] = i % 2 == 0 ? true : false;
        }
        return booleanIsReadArray;
    }

    private MessageDirectionViewModel[] createMessageDirectionArray() {
        MessageDirectionViewModel[] enumDirectionArray = new MessageDirectionViewModel[12];
        for (int i = 0; i < enumDirectionArray.length; i++) {
            enumDirectionArray[i] = i % 2 == 0 ? MessageDirectionViewModel.INCOMING : MessageDirectionViewModel.OUTGOING;
        }
        return enumDirectionArray;
    }
}
