package com.example.user.android_class_26;

import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mesg;
    private MyAsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesg = (TextView)findViewById(R.id.mesg);

    }

    public void Button(View v) {
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("ppking","sweer","soeee","soew");
    }

    public void Button2(View v) {
        if (myTask != null && !myTask.isCancelled()){
            myTask.cancel(true);
        }

    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("ppking","onPreExecute");
        }

        @Override
        protected String doInBackground(String... names) {
            int i = 0; String ret = "OK";
            for (String name : names) {
                Log.v("ppking", "doInBackground:" + name);
                publishProgress(i, i+10, i+100);
                try {
                    Thread.sleep(500);
                    if (isCancelled()){
                        ret = "cancel";
                        break;
                    }
                }catch (Exception e){}
                i++;
            }
            return ret;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mesg.setText("onProgressUpdate:" + values[0] + ":" +
                    values[1] + ":" + values[2]);

        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            Log.v("ppking","onPostExecute");
        }


        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
            Log.v("ppking","onCancelled");
        }
    }
}
