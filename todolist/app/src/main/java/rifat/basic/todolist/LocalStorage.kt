package rifat.basic.todolist
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalStorage {

    private  val PREFS_NAME = "my_preferences"
    private  val KEY_MY_LIST = "my_list"

    private val gson = Gson()

    fun saveList(context: Context, item: Todo) {
        val existingList = getList(context).toMutableList()
        existingList.add(item)

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val serializedList = gson.toJson(existingList)
        editor.putString(KEY_MY_LIST, serializedList)
        editor.apply()
    }this

    fun getList(context: Context): List<Todo> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val serializedList = prefs.getString(KEY_MY_LIST, null)
        return if (serializedList != null) {
            gson.fromJson(serializedList, object : TypeToken<List<Todo>>() {}.type)
        } else {
            emptyList()
        }
    }
}
