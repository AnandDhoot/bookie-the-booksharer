package com.iitb.root.booksharer;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;


public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener {



    private MainFragment mainFragment;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void goButtonOnClick(View v){
       if(mainFragment.loggedIn())
       {
           try {
               EditText userBox = (EditText) findViewById(R.id.userBox);
               Email.setUsername(userBox.getText().toString());


               JSONObject obj = new JSONObject();
               obj.put("type", "1");
               obj.put("name", Email.getUsername());
               obj.put("email", Email.getEmail());
               obj.put("password", "root");

               DataSender dataSender=new DataSender();
               String str=dataSender.sendJson(obj);

           }
           catch (Exception e){}
           Intent myIntent = new Intent(MainActivity.this, OptionsPage.class);
           MainActivity.this.startActivity(myIntent);

       }
       else{
           Context context = getApplicationContext();
           CharSequence text = "DUDE! Log in first!!! -_-";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       }
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Intent myIntent = new Intent(MainActivity.this, OptionsPage.class);
            MainActivity.this.startActivity(myIntent);
        } else if (state.isClosed()) {
            //Log.i(TAG, "Logged out...");
        }
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

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
