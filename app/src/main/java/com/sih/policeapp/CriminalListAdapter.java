package com.sih.policeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CriminalListAdapter extends RecyclerView.Adapter<CriminalListAdapter.ViewHolder> {

    private Context ctx;
    private List<Criminals> shortlistedCriminals;


    public CriminalListAdapter(Context ctx, List<Criminals> shortlistedCriminals) {
        this.ctx = ctx;
        this.shortlistedCriminals = shortlistedCriminals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_criminal_list_view, parent, false);
        return new CriminalListAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CircleImageView circleImageView;
        TextView name,rating,lastCrime;

        circleImageView = holder.mView.findViewById(R.id.criminal_profile_pic);
        name = holder.mView.findViewById(R.id.criminal_name);
        rating = holder.mView.findViewById(R.id.criminals_rating);
        lastCrime = holder.mView.findViewById(R.id.last_crime);

        Criminals currCriminal = shortlistedCriminals.get(position);

        Picasso.with(ctx)
                .load(currCriminal.getProfile_pic_url())
                .placeholder(R.drawable.avtar)
                .into(circleImageView);

        name.setText(currCriminal.getCriminal_name());
        String rat = currCriminal.getCriminal_rating();
        if(rat.equals("1")||rat.equals("2")) ;
        else if(rat.equals("3")) rating.setTextColor(Color.parseColor("#6c6c6c"));  //darkGrey
        else if(rat.equals("5")) rating.setTextColor(Color.parseColor("#E01010"));  //Red
        else if(rat.equals("4")) rating.setTextColor(Color.parseColor("#F57C00"));  //Orange
        String ans = "Criminal Rating: " + currCriminal.getCriminal_rating();
        rating.setText(ans);
       // lastCrime



    }

    @Override
    public int getItemCount() {
        return shortlistedCriminals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
}
