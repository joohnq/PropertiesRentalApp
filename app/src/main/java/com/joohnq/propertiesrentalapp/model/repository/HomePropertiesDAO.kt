//package com.joohnq.propertiesrentalapp.model.repository
//
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Query
//import androidx.room.RoomDatabase
//import com.joohnq.propertiesrentalapp.model.entities.PropertyItem
//import com.joohnq.propertiesrentalapp.util.Constants.DATABASE_TABLE_HOME_PROPERTIES
//
//@Dao
//interface HomePropertyDAO {
//    @Query("SELECT * FROM $DATABASE_TABLE_HOME_PROPERTIES")
//    fun getAll(): List<PropertyItem>
//}
//
//@Database(entities = [PropertyItem::class], version = 1)
//abstract class HomeProperty : RoomDatabase() {
//    abstract fun homePropertyDAO(): HomePropertyDAO
//}