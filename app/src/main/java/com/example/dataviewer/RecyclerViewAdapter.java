package com.example.dataviewer;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context c;
    ArrayList<Model> models;
    public  RecyclerViewAdapter(Context c) {
        this.c=c;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        holder.name.setText(models.get(position).getName());
        holder.date.setText(models.get(position).getTime());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(c,AllFields.class);
                String n=models.get(position).getName();
                String e=models.get(position).getMail();
                String p=models.get(position).getContact_no();
                String s=models.get(position).getSubOfMessege();
                String m=models.get(position).getMessege();
                intent.putExtra("name",n);
                intent.putExtra("email",e);
                intent.putExtra("ph",p);
                intent.putExtra("sub",s);
                intent.putExtra("msg",m);
                c.startActivity(intent);
            }
        });
    }

    public void setModels(ArrayList<Model> models) {
        this.models = models;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    public  class ViewHolder extends  RecyclerView.ViewHolder {
        CardView parent;
        TextView name,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
        }
    }
}
