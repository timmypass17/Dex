package android.example.dex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeService {

    @GET("cards")
    Call<PokeResponse> getPokemons(@Query("q") String query);
}
