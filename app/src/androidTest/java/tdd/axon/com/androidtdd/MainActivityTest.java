package tdd.axon.com.androidtdd;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Unit Test for MainActivityTest
 *
 * @author Thang Cao
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityTestRule<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        editTextPassword = (EditText) activityTestRule.getActivity().findViewById(R.id.edit_text_password);
        editTextUsername = (EditText) activityTestRule.getActivity().findViewById(R.id.edit_text_user_name);
        buttonLogin = (Button) activityTestRule.getActivity().findViewById(R.id.button_login);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUserNameField() {
        assertNotNull(editTextUsername);
        assertEquals("Username", editTextUsername.getHint().toString());
    }

    @Test
    public void testPasswordField() {
        assertNotNull(editTextPassword);
        assertEquals("Password", editTextPassword.getHint().toString());
    }

    @Test
    public void testButtonLogin() {
        assertNotNull(buttonLogin);
        assertEquals("Login", buttonLogin.getText().toString());
    }

    @Test
    public void testLoginSuccessfully() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editTextUsername.setText("hcmc-x");
                editTextPassword.setText("X-happen123");
                buttonLogin.callOnClick();
            }
        });

        solo.waitForText(activityTestRule
                .getActivity()
                .getString(R.string.msg_login_successfully));
    }

    @Test
    public void testLoginFailed_Username_IsEmpty() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editTextUsername.setText("");
                editTextPassword.setText("12345");
                buttonLogin.callOnClick();
            }
        });

        solo.waitForText(activityTestRule.getActivity().getString((R.string.msg_empty_username_or_password)));
    }

    @Test
    public void testLoginFailed_Password_IsEmpty() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editTextUsername.setText("ThangCao");
                editTextPassword.setText("");
                buttonLogin.callOnClick();
            }
        });
        solo.waitForText(activityTestRule.getActivity().getString((R.string.msg_empty_username_or_password)));
    }

    @Test
    public void testLoginFailed_Username_And_Password_IsEmpty() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editTextUsername.setText("");
                editTextPassword.setText("");
                buttonLogin.callOnClick();
            }
        });
        solo.waitForText(activityTestRule.getActivity().getString((R.string.msg_empty_username_or_password)));
    }
}