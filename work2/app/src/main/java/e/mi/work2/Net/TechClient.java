package e.mi.work2.Net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TechClient {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    static Retrofit retrofit;
    public static Retrofit getTechClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public TechApi getTechApi() {
        return retrofit.create(TechApi.class);
    }
}
