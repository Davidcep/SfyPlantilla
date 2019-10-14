package es.sfy.sfyall.View.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.sfy.sfyall.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView title;
    ImageView image;
    TextView date;
    RecyclerViewClick viewClick;

    public RecyclerViewHolder(@NonNull View itemView, RecyclerViewClick viewClick) {
        super(itemView);

        title = itemView.findViewById(R.id.tv_card);
        image = itemView.findViewById(R.id.iv_card);
        date = itemView.findViewById(R.id.tv_card_date);
        this.viewClick = viewClick;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        viewClick.onClick(getAdapterPosition());
    }
}
