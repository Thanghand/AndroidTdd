package tdd.axon.com.androidtdd;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest extends ActivityTestRule<LoginActivity> {


    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private Solo solo;

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonLogin;

    public LoginActivityTest(Class<LoginActivity> activityClass) {
        super(activityClass);
    }

    @Before
    public void setup() {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        editTextUserName = (EditText) getViewByID(R.id.activity_login_username);
    }

    @Test
    public void testInitUsername() {
        assertNotNull(editTextUserName);
        assertEquals(editTextUserName.getHint().toString(), getStringID(R.string.username_login));
    }

    private View getViewByID(int viewId) {
        return activityTestRule.getActivity().findViewById(viewId);
    }

    private String getStringID(int id) {
        return activityTestRule.getActivity().getString(id);
    }
}
