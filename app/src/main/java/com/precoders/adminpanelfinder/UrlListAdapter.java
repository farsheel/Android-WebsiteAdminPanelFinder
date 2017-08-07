package com.precoders.adminpanelfinder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Farsheel on 6/8/17.
 */

public class UrlListAdapter extends RecyclerView.Adapter<UrlListAdapter.UrlViewHolder> {

    List<UrlModel> urlModel;


    public UrlListAdapter(List<UrlModel> urlModel) {
        this.urlModel=urlModel;

    }
    @Override
    public UrlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View urlItemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_row,parent,false);


        return new UrlViewHolder(urlItemView);
    }

    @Override
    public void onBindViewHolder(final UrlViewHolder holder, int position) {
        UrlModel um=urlModel.get(position);

        final String url=um.getUrl();
        holder.url.setText(um.getUrl());
        holder.status.setText("Response Code:"+um.getStatus()+"(Found Admin Panel)");
        if(um.getStatus().equals("302"))
        {
            holder.status.setText("Response Code:"+um.getStatus()+"(Maybe Admin Panel..!)");
        }




    }

    @Override
    public int getItemCount() {
        return urlModel.size();
    }

    public class UrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView url,status;
        public Button btnCopy;
        public UrlViewHolder(View itemView) {
            super(itemView);
            url= (TextView) itemView.findViewById(R.id.tvUrl);
            status=(TextView) itemView.findViewById(R.id.tvResponse);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int pos=getAdapterPosition();
            String url=urlModel.get(pos).getUrl();





            ClipboardManager myClipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);

            ClipData myClip = ClipData.newPlainText("AdminUrl", url);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(v.getContext(), "Copied to clipboard" , Toast.LENGTH_SHORT ).show();


        }

    }
}
