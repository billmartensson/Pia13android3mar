package se.magictechnology.pia13android3mar

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Shopitem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val shopname : String,
    val amount : Int,
    val isbought : Boolean
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

@Database(entities = [Shopitem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopitemDao(): ShopitemDao
}