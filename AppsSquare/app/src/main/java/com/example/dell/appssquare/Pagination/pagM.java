package com.example.dell.appssquare.Pagination;

/**
 * Created by DELL on 27/09/2017.
 */

public class pagM {

             /*  adapter = new PaginationAdapter(mRepositryList,getApplicationContext());//
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());//
                mRecyclerView.setAdapter(adapter);

                mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                    @Override
                    protected void loadMoreItems() {
                        isLoading = true;
                        currentPage += 1;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                loadNextPage();

                            }
                        },1000);


                    }

                    @Override
                    public int getTotalPageCount() {
                        return TOTAL_PAGES;
                    }

                    @Override
                    public boolean isLastPage() {
                        return isLastPage;
                    }

                    @Override
                    public boolean isLoading() {
                        return isLoading;
                    }
                });


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadFirstPage();
                    }
                }, 1000);*/

    /*
    private void loadFirstPage() {

        List<Repository> reposes = Repository.createRepositry(adapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        adapter.addAll(reposes);

        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;

    }

    private void loadNextPage() {

        List<Repository> reposes = Repository.createRepositry(adapter.getItemCount());

        adapter.removeLoadingFooter();
        isLoading = false;

        adapter.addAll(reposes);

        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;
    }*/

}
