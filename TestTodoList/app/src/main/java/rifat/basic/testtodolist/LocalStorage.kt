package rifat.basic.testtodolist

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalStorage {
    val gson = Gson()
    fun getList(context: Context) : List<Map<String, Any>> {
        val sharedPreferences = context.getSharedPreferences(
            "todo", Context.MODE_PRIVATE)
        val listLocal = sharedPreferences.getString("data", null)
        if (listLocal != null){
           return gson.fromJson(listLocal, object : TypeToken<List<Map<String, Any>>>() {}.type)
        } else {
            return emptyList()
        }
    }

    fun saveList(context: Context, item: Map<String,Any>) {
        val listFromLocal = getList(context).toMutableList()
        listFromLocal.add(item)

        val sharedPreferences = context.getSharedPreferences(
            "todo", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(listFromLocal)
        editor.putString("data", json)
        editor.apply()
    }
}