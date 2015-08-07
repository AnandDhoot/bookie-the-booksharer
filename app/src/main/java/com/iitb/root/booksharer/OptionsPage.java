package com.iitb.root.booksharer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class OptionsPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_page, menu);
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

    public void addBookButton1OnClick(View v){
        Intent myIntent = new Intent(OptionsPage.this, addBookActivity.class);
        OptionsPage.this.startActivity(myIntent);
    }


    public void searchBookButton1OnClick(View v) throws JSONException{
        DataSender dataSender=new DataSender();

        JSONObject obj= new JSONObject();
        EditText ed = (EditText)findViewById(R.id.searchBox);
        obj.put("type", "4");
        obj.put("id", "1");
        obj.put("email", Email.getEmail());
        obj.put("password", "root");
        obj.put("query", ed.getText());
        obj.put("flag", "1");

        String searchJson=dataSender.sendJson(obj);

        Intent myIntent = new Intent(OptionsPage.this, searchBookActivity.class);
        myIntent.putExtra("searchJson",searchJson);
        OptionsPage.this.startActivity(myIntent);
    }

    public void showBookButton1OnClick(View v) throws JSONException {

        DataSender dataSender=new DataSender();

        JSONObject obj= new JSONObject();
        obj.put("type", "0");
        obj.put("email", Email.getEmail());
        obj.put("password", "root");

        String showJson=dataSender.sendJson(obj);

        Intent myIntent = new Intent(OptionsPage.this, showBookActivity.class);
        myIntent.putExtra("showJson",showJson);
        OptionsPage.this.startActivity(myIntent);
    }
}
