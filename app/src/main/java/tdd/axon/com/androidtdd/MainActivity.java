package tdd.axon.com.androidtdd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private String username = "hcmc-x";
    private String password = "X-happen123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mappingUI();
        intUIHandleEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isUsernameOrPasswordEmpty() {
        return editTextUsername.getText().toString().isEmpty()
                || editTextPassword.getText().toString().isEmpty();
    }

    private boolean isValidAccount() {
        return editTextUsername.getText().equals(username)
                && editTextPassword.getText().equals(password);
    }

    private void mappingUI() {
        buttonLogin = (Button) findViewById(R.id.button_login);
        editTextUsername = (EditText) findViewById(R.id.edit_text_user_name);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
    }

    private void intUIHandleEvents() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidAccount()) {
                    Toast.makeText(getApplicationContext(),
                            R.string.msg_login_successfully,
                            Toast.LENGTH_SHORT).show();
                } else if (isUsernameOrPasswordEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            R.string.msg_empty_username_or_password,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
