package com.example.jia.mobileassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerDetails extends AppCompatActivity {
    ListView listViewCustomer;
    List<Customer> caList;
    private ProgressDialog pDialog;
    private static String address;
    private static int pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();
        pid = Integer.parseInt(intent.getStringExtra("ITEM"));

        listViewCustomer = (ListView) findViewById(R.id.listView);
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
                downloadCourse(this, getResources().getString(R.string.get_course_url));
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

                           // for(int i=0; i < response.length();i++){
                            //pass position here
                                JSONObject courseResponse = (JSONObject) response.get(pid);
                                String id = courseResponse.getString("id");
                                String name = courseResponse.getString("name");
                                address = courseResponse.getString("address");
                                String phone = courseResponse.getString("phone");
                                String representative = courseResponse.getString("representative");
                                String contact = courseResponse.getString("contact");
                                String position = courseResponse.getString("position");
                                String email = courseResponse.getString("email");

                                Customer customer = new Customer();
                                customer.setId(id);
                                customer.setName(name);
                                customer.setAddress(address);
                                customer.setPhone(phone);
                                customer.setRepresentative(representative);
                                customer.setContact(contact);
                                customer.setPosition(position);
                                customer.setEmail(email);
                                caList.add(customer);
                         //  }
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
     //   Toast.makeText(getApplicationContext(), "Count :" + caList.size(), Toast.LENGTH_SHORT).show();
    }

    public void getDirect(View v){
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s", address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


}
