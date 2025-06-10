package ifp.mobile.mytodoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDb : RoomDatabase() {
    abstract fun getDao(): TodoDao

    companion object {
        private var db: TodoDb? = null

        fun getInstance(context: Context): TodoDb {
            val conn = db
            if(conn !== null) return conn
            synchronized(this) {
                val conn = Room.
                databaseBuilder(
                    context.applicationContext,
                    TodoDb::class.java,
                    "todo-db"
                ).build()
                this.db = conn
                return conn
            }
        }
    }

}