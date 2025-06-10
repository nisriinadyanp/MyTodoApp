package ifp.mobile.mytodoapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @POST("todos")
    fun addTodo(@Body todo: Todo): Call<Todo>

    @PUT("todos/{id}")
    fun updateTodo(@Path("id") id: Int?,
                   @Body todo: Todo): Call<Void>

    @DELETE("todos/{id}")
    fun deleteTodo(@Path("id") id: Int?): Call<Void>

}