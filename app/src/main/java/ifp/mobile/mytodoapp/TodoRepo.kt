package ifp.mobile.mytodoapp

import android.content.Context

interface ITodoRepo { //utk manipulasi data, ViewModel akan percaya pada model yg menggunakan interface ITodoRepo
    suspend fun getAll(): List<Todo>
    suspend fun add(todo: Todo): Boolean
    suspend fun update(todo: Todo): Boolean
    suspend fun delete(todo: Todo): Boolean
}

class TodoRepo(context: Context) : ITodoRepo { //repo jalannya asyncron maka pake fungsi callback yg dipanggil kl fungsi sebelumnya udah selesai

    val db = TodoDb.getInstance(context)

    override suspend fun getAll(): List<Todo> {
        try {
            val res = RetrofitClient.apiService.getTodos()
                .execute();
            return res.body() ?: emptyList()
        } catch (ex: Exception) {
             return db.getDao().getAll()
        }
    }

    override suspend fun add(todo: Todo): Boolean {
        try {
            val res = RetrofitClient.apiService.addTodo(todo)
                .execute();
            return res.isSuccessful
        } catch (ex: Exception) {
            db.getDao().insertAll(todo)
            return true;
        }
    }

    override suspend fun update(todo: Todo): Boolean {
        try {
            val res = RetrofitClient.apiService.updateTodo(todo.id, todo)
                .execute();
            return res.isSuccessful
        } catch (ex: Exception) {
            db.getDao().update(todo)
            return true;
        }
    }

    override suspend fun delete(todo: Todo): Boolean {
        try {
            val res = RetrofitClient.apiService.deleteTodo(todo.id)
                .execute();
            return res.isSuccessful
        } catch (ex: Exception) {
            db.getDao().delete(todo)
            return true;
        }
    }
}