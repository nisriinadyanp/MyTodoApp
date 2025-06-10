package ifp.mobile.mytodoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getAllCompleted(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun findById(id: Int) : Todo

    @Insert
    fun insertAll(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun update(todo: Todo)
}