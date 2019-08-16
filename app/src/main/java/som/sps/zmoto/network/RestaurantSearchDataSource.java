package som.sps.zmoto.network;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import som.sps.zmoto.model.RestaurantResponse;
import som.sps.zmoto.model.Restaurants;
import timber.log.Timber;


public class RestaurantSearchDataSource extends PageKeyedDataSource<Integer, Restaurants> {


    private static final int FIRST_PAGE = 0;
    private final int entityId;
    private final String entityType;
    private final int category;


    RestaurantSearchDataSource(int entityId, String entityType, int category){
        this.entityId = entityId;
        this.entityType = entityType;
        this.category = category;
    }
    @Override
    public void loadInitial(final @NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Restaurants> callback) {
        RetrofitClient.INSTANCE.getZomotoService()
                .fetchRestaurants(FIRST_PAGE,entityId,entityType,params.requestedLoadSize,category)
                .enqueue(new Callback<RestaurantResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                        RestaurantResponse body = response.body();
                        if (body != null) {
                            Integer key = (body.getResultsFound() -body.getResultsStart()-body.getResultsShown())>1? FIRST_PAGE+1 : null;
                            callback.onResult(body.getRestaurants(), null,key);
                        }else{
                            Timber.i(response.message()+", "+response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                        Timber.e(t);
                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Restaurants> callback) {
     final   Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
        RetrofitClient.INSTANCE.getZomotoService()
                .fetchRestaurants(params.key*params.requestedLoadSize,entityId,entityType,params.requestedLoadSize,category)
                .enqueue(new Callback<RestaurantResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getRestaurants(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                        Timber.e(t);
                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Restaurants> callback) {
        RetrofitClient.INSTANCE.getZomotoService()
                .fetchRestaurants(params.key*params.requestedLoadSize,entityId,entityType,params
                        .requestedLoadSize,category)
                .enqueue(new Callback<RestaurantResponse>() {
                    @Override
                    public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                        RestaurantResponse body = response.body();
                        if (body != null) {
                            Integer key = (body.getResultsFound() -body.getResultsStart()-body.getResultsShown())>1? params.key + 1 : null;
                            callback.onResult(body.getRestaurants(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                        Timber.e(t);
                    }
                });

    }
}