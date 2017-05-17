package adrian.trivia;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Adrian on 5/16/2017.
 */

public interface Trivia {
    @GET("/api.php")
    Call<Questions> getQuestionsFull(@Query("amount") String amount, @Query("category") String category, @Query("difficulty") String difficulty, @Query("type") String type);

    @GET("/api.php")
    Call<Questions> getQuestionsOnlyWithAmount(@Query("amount") String amount);

    @GET("/api.php")
    Call<Questions> getQuestionsWithoutCategory(@Query("amount") String amount, @Query("difficulty") String difficulty, @Query("type") String type);

    @GET("/api.php")
    Call<Questions> getQuestionsWithoutDifficulty(@Query("amount") String amount, @Query("category") String category, @Query("type") String type);

    @GET("/api.php")
    Call<Questions> getQuestionsWithoutType(@Query("amount") String amount, @Query("category") String category, @Query("difficulty") String difficulty);

    @GET("/api.php")
    Call<Questions> getQuestionsWithType(@Query("amount") String amount, @Query("type") String type);

    @GET("/api.php")
    Call<Questions> getQuestionsWithCategory(@Query("amount") String amount, @Query("category") String category);

    @GET("/api.php")
    Call<Questions> getQuestionsWithDifficulty(@Query("amount") String amount, @Query("difficulty") String difficulty);

    class Service {
        private static Trivia sInstance;

        public static Trivia Get() {
            if(sInstance == null) {
                sInstance = new Retrofit.Builder().baseUrl("https://opentdb.com")
                        .addConverterFactory(GsonConverterFactory.create()).build().create(Trivia.class);
            }
            return sInstance;
        }
    }
}
