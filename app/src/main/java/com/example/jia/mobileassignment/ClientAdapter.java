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
 * Created by JiaYu on 12/12/2015.
 */
public class ClientAdapter extends ArrayAdapter<Client> {
    List<Client> list;
    Activity context;

    public ClientAdapter(Activity context, List<Client> l) {
        super(context, R.layout.client_record, l);
        this.list = l;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.client_record, parent, false);

        TextView textViewName, textViewAddress;

        textViewName = (TextView)rowView.findViewById(R.id.textViewName);
        textViewAddress = (TextView)rowView.findViewById(R.id.textViewAddress);


        textViewName.setText("Customer Name:"+list.get(position).getName());
        textViewAddress.setText("Address:" + list.get(position).getAddress());

        return rowView;
    }
}