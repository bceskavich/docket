package com.bcpk.docket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcpk.docket.Location;
import com.bcpk.docket.R;

import java.util.ArrayList;
import java.util.List;


public class TipsAdapter extends ArrayAdapter<Tip> {

    Context context;

    public TipsAdapter(Context context, int resourceId,
                           List<Tip> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Tip tipItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.singletip_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.tip);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(tipItem.getDesc());

        return convertView;
    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtDesc;
    }
}
