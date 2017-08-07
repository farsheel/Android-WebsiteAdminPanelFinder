package com.precoders.adminpanelfinder;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farsheel on 6/8/17.
 */

public class AdminPanel extends AppCompatActivity {

    RecyclerView rvUrlList;
    ProgressDialog pd=null;
    String urlText,fileType;

    UrlListAdapter urlListAdapter;
    List<UrlModel> urlsFound=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Bundle extras=getIntent().getExtras();
        urlText=extras.getString("url");
        fileType=extras.getString("file");
        urlListAdapter=new UrlListAdapter(urlsFound);
        rvUrlList=(RecyclerView) findViewById(R.id.rvUrlList);
        rvUrlList.setLayoutManager(new LinearLayoutManager(this));

        pd=new ProgressDialog(this);
        rvUrlList.setAdapter(urlListAdapter);

        AdminFinderTask adt=new AdminFinderTask();
        adt.execute();


    }

    private class AdminFinderTask extends AsyncTask<Void,String,String>
    {
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            for (String value : values) {
                pd.setMessage("Checking.. \n" + value);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Finding Admin Panel...");
            pd.setMessage("Please wait.... it will take some minutes");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.cancel();
            urlListAdapter.notifyDataSetChanged();

        }

        @Override
        protected String doInBackground(Void... params) {
            try {


                URL url=null;
                String[] panelList=null;
                if(fileType.equalsIgnoreCase("php"))
                {
                    panelList= AdminPanelList.php.clone();
                }
                else if(fileType.equalsIgnoreCase("asp")) {
                    panelList= AdminPanelList.asp.clone();
                }
                else if(fileType.equalsIgnoreCase("js")) {
                    panelList= AdminPanelList.js.clone();
                }
                else if(fileType.equalsIgnoreCase("cgi")) {
                    panelList= AdminPanelList.cgi.clone();
                }
                else if(fileType.equalsIgnoreCase("cfm")) {
                    panelList= AdminPanelList.cfm.clone();
                }
                else if(fileType.equalsIgnoreCase("brf")) {
                    panelList= AdminPanelList.brf.clone();
                }
                for (String i:panelList)
                {

                    String urlCon=urlText+"/"+i;
                    url=new URL(urlCon);
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(1500);
                    con.connect();
                    publishProgress(urlCon);
                    if(con.getResponseCode()==200)
                    {
                        UrlModel um=new UrlModel(urlCon,"200");
                        urlsFound.add(um);

                    }
                    else if(con.getResponseCode()==302)
                    {
                        UrlModel um=new UrlModel(urlCon,"302");
                        urlsFound.add(um);

                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }


}
