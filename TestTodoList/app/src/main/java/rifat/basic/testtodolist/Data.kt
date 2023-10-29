package rifat.basic.testtodolist

var itemList = mutableListOf<Map<String,Any>>(
  mapOf(
      "id" to 1,
      "title" to "Lari Pagi",
      "desc" to "Di Jakarta pagi hari",
      "date" to "30-10-2023",
      "complete" to false
  ),
    mapOf(
        "id" to 2,
        "title" to "Sarapan Pagi",
        "desc" to "Dini hari",
        "date" to "28-10-2023",
        "complete" to true
    ),
    mapOf(
        "id" to 3,
        "title" to "Bermain Bulu tangkis",
        "desc" to "Di Jakarta pagi hari",
        "date" to "29-10-2023",
        "complete" to false
    )
)