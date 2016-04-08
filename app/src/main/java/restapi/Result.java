package restapi;

import java.util.List;

/**
 * Created by jeffersonalmeida on 4/4/16.
 */
public class Result {

    private String status;
    private int count;
    private List<Posts> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}
