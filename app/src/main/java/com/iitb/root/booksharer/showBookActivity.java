package com.iitb.root.booksharer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class showBookActivity extends ActionBarActivity {
    String showJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_book);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                showJson = extras.getString("showJson");
            }

            JSONObject jsonObject=new JSONObject(showJson);

            Iterator<String> iter = jsonObject.keys();
            while(iter.hasNext()){
                String key = (String)iter.next();
                if(key.equals("status")||key.equals("a")){
                    continue;
                }

                JSONObject jsonObject1=jsonObject.getJSONObject(key);
                String id=jsonObject1.getString("id");
                String title=jsonObject1.getString("title");
                String userid=jsonObject1.getString("email");

                final TextView output = new TextView(this);
                output.setText("Title:" + title + " " + "Email:" + userid );
                output.setTag(id);

                output.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            DataSender dataSender = new DataSender();

                            JSONObject obj = new JSONObject();
                            obj.put("type","3");
                            obj.put("id", output.getTag());
                            obj.put("email", Email.getEmail());
                            obj.put("password", "root");

                            String showJson = dataSender.sendJson(obj);


                        }
                        catch (Exception e){

                        }
                    }
                });



                LinearLayout showLayout= (LinearLayout)findViewById(R.id.showResultLayout);
                showLayout.addView(output);
            }

        }
        catch (JSONException e){}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_book, menu);
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

    public void BackButtonOnClick(View v){
        Intent myIntent = new Intent(showBookActivity.this, OptionsPage.class);
        showBookActivity.this.startActivity(myIntent);
    }


}
