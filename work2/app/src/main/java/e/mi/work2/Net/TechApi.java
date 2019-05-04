package e.mi.work2.Net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TechApi {
    @GET("wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json")
    Call<List<TechItem>> getTech();
}
