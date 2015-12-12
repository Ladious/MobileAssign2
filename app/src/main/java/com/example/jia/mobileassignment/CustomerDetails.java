package com.example.jia.mobileassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CustomerDetails extends AppCompatActivity {
    ListView listViewCustomer;
    List<Customer> caList;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        readCustomer();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void readCustomer() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                //new downloadCourse().execute(getResources().getString(R.string.get_course_url));
                downloadCustomer(this, getResources().getString(R.string.get_course_url));
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

    private void downloadCustomer(Context context, String url) {
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

                            for(int i=0; i < response.length();i++){

                                JSONObject customerResponse = (JSONObject) response.get(i);
                                String id = customerResponse.getString("id");
                                if(id.equals("1000")){
                                    String name = customerResponse.getString("name");
                                    String address = customerResponse.getString("address");
                                    String phone = customerResponse.getString("phone");
                                    String representative = customerResponse.getString("representative");
                                    String contact = customerResponse.getString("contact");
                                    String position = customerResponse.getString("position");
                                    String email = customerResponse.getString("email");

                                    Customer customer = new Customer();
                                    customer.setName(name);
                                    customer.setAddress(address);
                                    customer.setPhone(phone);
                                    customer.setRepresentative(representative);
                                    customer.setContact(contact);
                                    customer.setPosition(position);
                                    customer.setEmail(email);
                                    caList.add(customer);
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
        final CustomerAdapter adapter = new CustomerAdapter(this, caList);
        listViewCustomer.setAdapter(adapter);
       // Toast.makeText(getApplicationContext(), "Count :" + caList.size(), Toast.LENGTH_SHORT).show();
    }

}
