package com.jltechnologies.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jeff on 12/14/16.
 */

public class ListAdapter extends BaseAdapter{
    private final Context mContext;
    private final Hole[] mHoles;


    public ListAdapter(Context context, Hole[] holes){
        mContext = context;
        mHoles = holes;
    }
    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int i) {
        return mHoles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        final ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView) convertView.findViewById(R.id.strokeCount);
            holder.removeStrokeButton = (Button) convertView.findViewById(R.id.removeStrokeButton);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.holeLabel.setText(mHoles[i].getLabel());
        holder.strokeCount.setText(mHoles[i].getStrokeCount() + "");
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updateStrokeCount = mHoles[i].getStrokeCount()-1;
                if(updateStrokeCount < 0) {
                    updateStrokeCount = 0;
                }
                mHoles[i].setStrokeCount(updateStrokeCount);
                holder.strokeCount.setText(updateStrokeCount + "");
            }
        });
        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updateStrokeCount = mHoles[i].getStrokeCount()+1;
                mHoles[i].setStrokeCount(updateStrokeCount);
                holder.strokeCount.setText(updateStrokeCount + "");
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeCount;
        Button removeStrokeButton;
        Button addStrokeButton;
    }
}
