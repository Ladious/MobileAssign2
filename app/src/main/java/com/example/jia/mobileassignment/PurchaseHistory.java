package com.example.jia.mobileassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory extends AppCompatActivity {
    ListView listViewHistory;
    List<History> caList;
    private ProgressDialog pDialog;
    private static String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        Intent intent = getIntent();
        cid = intent.getStringExtra("ID");

        listViewHistory = (ListView) findViewById(R.id.listViewHistory);
        pDialog = new ProgressDialog(this);
        caList = new ArrayList<>();
        readCourse();


    }

    private void readCourse() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                //new downloadCourse().execute(getResources().getString(R.string.get_course_url));
                downloadCourse(this, getResources().getString(R.string.get_history_url));
            } else {
                Toast.makeText(getApplication(), "Network is NOT available",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(),
                    "Error reading record:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadCourse(Context context, String url) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (!pDialog.isShowing())
                            pDialog.show();

                        try{
                            //Clear list
                            caList.clear();

                             for(int i=0; i < response.length();i++) {
                                 JSONObject courseResponse = (JSONObject) response.get(i);
                                 String id = courseResponse.getString("id");

                                    if(id.equals(cid)) {
                                        String order_no = courseResponse.getString("order_no");
                                        String date = courseResponse.getString("date");
                                        String product_name = courseResponse.getString("product_name");
                                        String product_qty = courseResponse.getString("product_qty");


                                         History history = new History();
                                        history.setOrder_no(order_no);
                                         history.setDate(date);
                                        history.setProduct_name(product_name);
                                         history.setProduct_qty(product_qty);
                                        history.setId(id);

                                        caList.add(history);
                                    }
                                 }
                                 loadCourse();

                            if (pDialog.isShowing())
                                pDialog.dismiss();

                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "Error:" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void loadCourse() {
        final HistoryAdapter adapter = new HistoryAdapter(this, caList);
        listViewHistory.setAdapter(adapter);
        //   Toast.makeText(getApplicationContext(), "Count :" + caList.size(), Toast.LENGTH_SHORT).show();
    }



}
