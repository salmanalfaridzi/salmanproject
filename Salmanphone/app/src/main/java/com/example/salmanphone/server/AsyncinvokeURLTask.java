package com.example.salmanphone.server;

import android.app.ProgressDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AsyncinvokeURLTask extends AsyncTask<void, void, String> {
    public String mNoteItWebUrl = "www.smartneasy.com";
    private ArrayList<NameValuePair> mParams;
    private OnPostExecuteListener onPostExecuteListener = null;
    private ProgressDialog dialog;
    public boolean showdialog = false;
    public String message ="Proses Data";
    public String url_server ="http://localhost/xphone/";
    public context applicationcontext;
    public static interface OnPostExecutelistener {
        void omPostExecute(String result);
    }
    public AsyncinvokeURLTask(
            ArrayList<NameValuePair> nameValuePairs
            OnPostExecuteListener postExecuteListener) throws Exception{
        mParams = nameValuePairs;
        onPostExecuteListener = postExecuteListener;
        if (onPostExecuteListener == null)
            throw new Exception("Param cannot be null.");
    }
    @Override
    public void onPreExecute() {
        if (showdialog)
            this.dialog = ProgressDialog.show(applicationcontext.message, message: "Silahkan Menunggu...",true);

    }
    @Override
    public String doInBackground(void... params){
        String result = "timeout";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url_server+mNoteItWebUrl);
        try {
            httppost.setEntity(new UrlEncodedFromEntity(mParams));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null){
                InputStream inStream = entity.getContent();
                result = convertStreamToString(inStream);
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void onPostExecute(String result){
        if (onPostExecuteListener != null){
            try {
                if (showdialog) this.dialog.dismiss();
                onPostExecuteListener.onPostExecute(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private  static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
