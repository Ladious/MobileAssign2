package com.example.jia.mobileassignment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TARC on 8/6/2015.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {
    List<Customer> list;
    Activity context;

    public CustomerAdapter(Activity context, List<Customer> l) {
        super(context, R.layout.customer_details, l);
        this.list = l;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.customer_details, parent, false);

        TextView textViewId, textViewCompany, textViewAdd, textViewPhone, textViewRepresentative, textViewContact,
        textViewPost, textViewEmail;

        textViewId = (TextView)rowView.findViewById(R.id.textViewId);
        textViewCompany = (TextView)rowView.findViewById(R.id.textViewCompany);
        textViewAdd = (TextView)rowView.findViewById(R.id.textViewAdd);
        textViewPhone = (TextView)rowView.findViewById(R.id.textViewPhone);
        textViewRepresentative = (TextView)rowView.findViewById(R.id.textViewRepresentative);
        textViewContact = (TextView)rowView.findViewById(R.id.textViewContact);
        textViewPost = (TextView)rowView.findViewById(R.id.textViewPost);
        textViewEmail = (TextView)rowView.findViewById(R.id.textViewEmail);

        textViewId.setText(list.get(position).getId());
        textViewCompany.setText(list.get(position).getName());
        textViewAdd.setText(list.get(position).getAddress());
        textViewPhone.setText(list.get(position).getPhone());
        textViewRepresentative.setText(list.get(position).getRepresentative());
        textViewContact.setText(list.get(position).getContact());
        textViewPost.setText(list.get(position).getPosition());
        textViewEmail.setText(list.get(position).getEmail());
        return rowView;
    }
}
