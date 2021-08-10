package android.example.dex.db.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeService {

    @GET("sets")
    Call<PokeSetResponse> getSets();

    @GET("cards")
    Call<PokeResponse> getPokemonByPage(@Query("page") int page);
}
