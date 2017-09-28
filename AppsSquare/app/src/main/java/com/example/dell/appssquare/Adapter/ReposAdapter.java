package com.example.dell.appssquare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.appssquare.Interfaces.ItemClickListener;
import com.example.dell.appssquare.Model.Repository;
import com.example.dell.appssquare.R;
import com.example.dell.appssquare.Activity.browsers.HtmlBrowser;
import com.example.dell.appssquare.Activity.browsers.OwnerBroswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 26/09/2017.
 */

public class ReposAdapter  extends RecyclerView.Adapter<ReposAdapter.RepoviewHolder> {

    boolean forkstate;

    Context context;
    LinearLayout linearLayout;
    private List<Repository> reposList= new ArrayList<>();

    public ReposAdapter(List<Repository> reposList , Context context) {
        this.reposList = reposList;
        this.context=context;

    }

    @Override
    public RepoviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.raw,parent,false);
        linearLayout= (LinearLayout) view.findViewById(R.id.rawLayout);
        return new RepoviewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RepoviewHolder holder, int position) {
        holder.Reponame.setText(reposList.get(position).getName());
        holder.Repodesc.setText(reposList.get(position).getDescription());
     holder.ownerName.setText(reposList.get(position).getOwner().getLogin());
      holder.htmlU.setText(reposList.get(position).getHtmlUrl());
     holder.ownerU.setText(reposList.get(position).getOwner().getHtmlUrl()) ;

       forkstate= reposList.get(position).getFork();
        if (forkstate == true){
            linearLayout.setBackgroundResource(R.color.white);
        }else {
            linearLayout.setBackgroundResource(R.color.lightGreen);

        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {
                if (isLongClick){

                    final String html= holder.htmlU.getText().toString();
                    final String owner= holder.ownerU.getText().toString();


                    AlertDialog.Builder mBuilder=new AlertDialog.Builder(view.getContext());
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
                    View view1= inflater.inflate(R.layout.customdialoge,null);
                    Button htmlBtn=(Button)view1.findViewById(R.id.htmlUrlBtn);
                    Button ownerBtn=(Button)view1.findViewById(R.id.ownerUrlBtn);
                    htmlBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(view.getContext(),HtmlBrowser.class);
                            intent.putExtra("html",html);
                            view.getContext().startActivity(intent);

                        }
                    });

                    ownerBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(view.getContext(),OwnerBroswer.class);
                            intent.putExtra("owner",owner);
                            view.getContext().startActivity(intent);

                        }
                    });

                    mBuilder.setView(view1);
                    AlertDialog dialog=mBuilder.create();
                    dialog.show();

                }else {
                    Toast.makeText(context,"Long Click , please",Toast.LENGTH_LONG).show();

                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return reposList.size();

    }





    public class RepoviewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener{


        private ItemClickListener itemClickListener;

        TextView Reponame,Repodesc,ownerName,htmlU,ownerU;

        public RepoviewHolder(View itemView) {
            super(itemView);

            Reponame=(TextView)itemView.findViewById(R.id.repoNameTV);
            Repodesc=(TextView)itemView.findViewById(R.id.descrptionTV);
            ownerName=(TextView)itemView.findViewById(R.id.ownerNameTV);
            htmlU=(TextView)itemView.findViewById(R.id.htmlU);
            ownerU=(TextView)itemView.findViewById(R.id.ownerU);

           itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

       }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);

        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);

            return true;
        }
    }

}
