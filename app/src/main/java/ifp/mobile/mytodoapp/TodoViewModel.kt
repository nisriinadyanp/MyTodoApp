package ifp.mobile.mytodoapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(context: Context) : ViewModel() {

    private val _todos =
        MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos
    val repo = TodoRepo(context)

    fun fetchTodos() {
        //viewmodelscope itu korutin (kaya thread tapi bukan)
        viewModelScope.launch {
//            RetrofitClient.apiService.getTodos()
//                .enqueue(object: Callback<List<Todo>> {
//                    override fun onResponse(
//                        call: Call<List<Todo>>,
//                        response: Response<List<Todo>>
//                    ) {
//                        if (response.isSuccessful) {
//                            _todos.value = response.body() ?: emptyList()
//                        }
//                    }
//                    override fun onFailure(
//                        call: Call<List<Todo>>,
//                        t: Throwable?
//                    ) {
//                        // kalau gagal ke sini
//                    }
//                })
        }
    }
    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
//            RetrofitClient.apiService.addTodo(todo)
//                .enqueue(object : Callback<Todo> {
//                    override fun onResponse(
//                        call: Call<Todo>,
//                        response: Response<Todo>
//                    ) {
//                        if (response.isSuccessful) fetchTodos()
//                    }
//                    override fun onFailure(
//                        call: Call<Todo>,
//                        t: Throwable?
//                    ) {
//                        TODO("Not yet implemented")
//                    }
//                })
        }
    }
    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
//            RetrofitClient.apiService.updateTodo(todo.id, todo)
//                .enqueue(object : Callback<Void> {
//                    override fun onResponse(
//                        call: Call<Void>,
//                        response: Response<Void>
//                    ) {
//                        if (response.isSuccessful)
//                            fetchTodos()
//                    }
//
//                    override fun onFailure(call: Call<Void?>?, t: Throwable?) {
//                        TODO("Not yet implemented")
//                    }
//                })
        }
    }
    fun deleteTodo(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
//            RetrofitClient.apiService.deleteTodo(id)
//                .enqueue(object : Callback<Void> {
//                    override fun onResponse(
//                        call: Call<Void>,
//                        response: Response<Void>
//                    ) {
//                        if (response.isSuccessful)
//                            fetchTodos()
//                    }
//
//                    override fun onFailure(call: Call<Void?>?, t: Throwable?) {
//                        TODO("Not yet implemented")
//                    }
//                })
        }
    }

    // import android.content.context
    fun addLocalTodo(context: Context, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            db.getDao().insertAll(todo)
        }
    }
    fun getAllLocalTodos(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            _todos.value = db.getDao().getAll()
        }
    }
    fun deleteLocalTodo(context: Context, todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            db.getDao().delete(todo)
            getAllLocalTodos(context)
        }
    }
    fun updateLocalTodo(context: Context, todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            val db = TodoDb.getInstance(context)
            db.getDao().update(todo)
            getAllLocalTodos(context)
        }
    }

    fun fetchTodosRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.value = repo.getAll()
        }
    }
    fun addTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.add(todo)) fetchTodosRepo()
        }
    }
    fun deleteTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.delete(todo)) fetchTodosRepo()
        }
    }
    fun updateTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.update(todo)) fetchTodosRepo()
        }
    }

}