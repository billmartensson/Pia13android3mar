package se.magictechnology.pia13android3mar

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Shopitem(
    val shopname : String,
    val amount : Int
)


@Dao
interface ShopitemDao {
    @Query("SELECT * FROM Shopitem")
    fun getAllShop() : List<Shopitem>

    @Insert
    fun addShop(shopitem: Shopitem)

    @Delete
    fun deleteshop(shopitem: Shopitem)
}

@Database(entities = [Shopitem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shipitemDao(): ShopitemDao
}