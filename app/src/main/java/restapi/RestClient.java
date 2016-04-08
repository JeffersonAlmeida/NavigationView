package restapi;

/**
 * Created by jeffersonalmeida on 4/4/16.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jefferson on 23/02/16.
 */
public class RestClient {

    private static final String BASE_URL = "http://www.jamal.com.br/"; //http://www.jamal.com.br/api/get_recent_posts/
    private RestInterface apiService;

    public RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RestInterface.class);
    }

    public RestInterface getApiService() {
        return apiService;
    }
}
