package ifp.mobile.mytodoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String = " ",

    @ColumnInfo(name = "completed")
    val completed: Boolean = false
)
