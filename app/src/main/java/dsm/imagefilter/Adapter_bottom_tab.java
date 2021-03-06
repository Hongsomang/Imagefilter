package dsm.imagefilter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by ghdth on 2018-09-14.
 */

public class Adapter_bottom_tab extends RecyclerView.Adapter<Adapter_bottom_tab.ViewHolder> {
    private Context context;
    private ArrayList<Item_bottom_tab> list=new ArrayList<Item_bottom_tab>();

    public Adapter_bottom_tab(Context context, ArrayList<Item_bottom_tab> list){
        this.context=context;
        this.list=list;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_tab,parent,false);
       ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tab_btn.setImageResource(list.get(position).getImage());
        Log.d("ddddddddddddd",list.get(position).getName());
        holder.name.setText(list.get(position).getName());
    }



    @Override
    public int getItemCount() {
        Log.d("dfdfdf",list.get(0).getName());
        return list.size() ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView tab_btn;
        private TextView name;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tab_btn=(ImageView)itemView.findViewById(R.id.tab_btn);
            name=(TextView)itemView.findViewById(R.id.tab_name);
            cardView=(CardView)itemView.findViewById(R.id.tab_cv);
        }
    }
}
