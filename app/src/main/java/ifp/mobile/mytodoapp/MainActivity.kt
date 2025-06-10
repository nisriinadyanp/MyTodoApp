package ifp.mobile.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ifp.mobile.mytodoapp.ui.theme.MyTodoAppTheme

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ifp.mobile.mytodoapp.ui.theme.MyTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                        innerPadding ->
                    HomeScreen(modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
    @Composable
    fun HomeScreen(modifier: Modifier) {
        val vm : TodoViewModel = TodoViewModel(LocalContext.current)
        val todos by vm.todos.collectAsStateWithLifecycle()
        val context = LocalContext.current
        var title by remember { mutableStateOf("") }

        Column(modifier = modifier) {
            Button(onClick = {
//                vm.fetchTodos()
//                vm.getAllLocalTodos(context)
                vm.fetchTodosRepo()
            }) {
                Text(text = "Ambil Todos!")
            }

            OutlinedTextField(value = title,
                onValueChange = { title = it },
                label = { Text("Title") })

            Button(onClick = {
                if (title.isNotEmpty()) {
                    val todo = Todo(title = title)
//                    vm.addTodo(todo)
//                    vm.addLocalTodo(context, todo)
                    vm.addTodoRepo(todo)
                }
            }) {
                Text("Add")
            }

            LazyColumn {
                items(todos) { todo ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = todo.title)
                        Checkbox(checked = todo.completed,
                            onCheckedChange = { checked ->
//                                vm.updateLocalTodo(context, todo.copy(completed = checked))
                                vm.updateTodoRepo(todo.copy(completed = checked))
                            })
                        IconButton(modifier = Modifier,
                            onClick = {
//                                vm.deleteLocalTodo(context, todo)
                                vm.deleteTodoRepo(todo)
                            }) {
                            Icon(imageVector = Icons.Default.DeleteForever,
                                tint = Color.Red,
                                contentDescription = "Delete")
                        }
                    }
                }
            }

        }

    }
}


