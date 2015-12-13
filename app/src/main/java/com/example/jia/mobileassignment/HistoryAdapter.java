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
 * Created by Ladious on 13/12/2015.
 */
public class HistoryAdapter extends ArrayAdapter<History> {
    List<History> list;
    Activity context;

    public HistoryAdapter(Activity context, List<History> l) {
        super(context, R.layout.purchase_record, l);
        this.list = l;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.purchase_record, parent, false);

        TextView textViewDate, textViewOrderNo, textViewProductName, textViewProductQty, textViewId;

        textViewDate = (TextView)rowView.findViewById(R.id.textViewDate);
        textViewOrderNo = (TextView)rowView.findViewById(R.id.textViewOrderNo);
        textViewProductName = (TextView)rowView.findViewById(R.id.textViewProductName);
        textViewProductQty = (TextView)rowView.findViewById(R.id.textViewProductQty);
        textViewId = (TextView)rowView.findViewById(R.id.textViewId);

        textViewOrderNo.setText(list.get(position).getOrder_no());
        textViewDate.setText(list.get(position).getDate());
        textViewProductName.setText(list.get(position).getProduct_name());
        textViewProductQty.setText(list.get(position).getProduct_qty());
        textViewId.setText(list.get(position).getId());

        return rowView;
    }



}
