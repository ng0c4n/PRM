package API;

import java.util.List;

import model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("todos")
    Call<List<User>> getUser();
}
