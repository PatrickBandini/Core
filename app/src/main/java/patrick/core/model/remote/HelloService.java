package patrick.core.model.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HelloService {

    // url da chiamare che restituisce un xml = http://baseurl.it/mService.php?id=ccxxxxxx&modalita=1

    @GET("mService.php")
    Call<Hello> xml(@Query("id") String id, @Query("modalita") String modalita);
}
