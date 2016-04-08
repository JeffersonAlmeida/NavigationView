package restapi;

/**
 * Created by jeffersonalmeida on 4/4/16.
 */
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jefferson on 23/02/16.
 */
public interface RestInterface {

    @GET("?json=get_tag_posts&tag_slug=artigos")
    Call<Result> getPosts();

    @GET("?json=1&count=1")
    Call<Result> getVideo();

    @GET("categorias/audio/?json=1&count=3")
    Call<Result> getAudio();

    @GET("api/get_recent_posts/?json=1&count=9999") ///get_recent_posts/?json=1&count=9999
    Call<Result> getRecentPosts();

}
