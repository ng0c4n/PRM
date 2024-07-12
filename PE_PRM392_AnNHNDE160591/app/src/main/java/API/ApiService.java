package API;

import java.util.List;

import models.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("photos")
    Call<List<Photo>> getPhoto();
}
