package com.example.dell.appssquare.Pagination;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
 * Created by DELL on 27/09/2017.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;

    LinearLayout linearLayout;
    private List<Repository> reposList= new ArrayList<>();
    private boolean isLoadingAdded = false;

    public PaginationAdapter(List<Repository> reposList,  Context context) {
        this.context = context;
        this.reposList =reposList;
    }

    public List<Repository> getRepos() {
        return reposList;
    }

    public void setRepos(List<Repository> repo) {
        this.reposList = repo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.raw, parent, false);

        linearLayout= (LinearLayout) v1.findViewById(R.id.rawLayout);
        viewHolder = new RepoviewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                final RepoviewHolder vHolder = (RepoviewHolder) holder;

                vHolder.Reponame.setText(reposList.get(position).getName());
                vHolder.Repodesc.setText(reposList.get(position).getDescription());
                vHolder.ownerName.setText(reposList.get(position).getOwner().getLogin());
                vHolder.htmlU.setText(reposList.get(position).getHtmlUrl());
                vHolder.ownerU.setText(reposList.get(position).getOwner().getHtmlUrl()) ;

                boolean forkstate= reposList.get(position).getFork();
                if (forkstate == true){
                    linearLayout.setBackgroundResource(R.color.white);
                }else {
                    linearLayout.setBackgroundResource(R.color.lightGreen);

                }

                vHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(final View view, int position, boolean isLongClick) {
                        if (isLongClick){

                            final String html= vHolder.htmlU.getText().toString();
                            final String owner= vHolder.ownerU.getText().toString();


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
                            Toast.makeText(context,"Thanks For Click",Toast.LENGTH_LONG).show();

                        }

                    }
                });

                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return reposList == null ? 0 : reposList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == reposList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers

    */

    public void add(Repository mc) {
        reposList.add(mc);
        notifyItemInserted(reposList.size() - 1);
    }

    public void addAll(List<Repository> mcList) {
        for (Repository mc : mcList) {
            add(mc);
        }
    }

    public void remove(Repository city) {
        int position = reposList.indexOf(city);
        if (position > -1) {
            reposList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Repository());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = reposList.size() - 1;
        Repository item = getItem(position);

        if (item != null) {
            reposList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Repository getItem(int position) {
        return reposList.get(position);
    }


   /*   View Holders     */
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


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
