package se.magictechnology.pia13android3mar

import androidx.room.AutoMigration
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

// Ändra databas

@Entity
data class Shopitem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val shopname : String,
    val amount : Int,
    var isbought : Boolean
)


@Dao
interface ShopitemDao {
    @Query("SELECT * FROM Shopitem")
    fun getAllShop() : List<Shopitem>

    @Query("SELECT * FROM Shopitem WHERE isbought = 1")
    fun getBoughtShop() : List<Shopitem>

    @Query("SELECT * FROM Shopitem WHERE isbought = 0")
    fun getNotBoughtShop() : List<Shopitem>

    @Query("SELECT * FROM Shopitem WHERE amount > :morethan")
    fun getMoreThanAmountShop(morethan : Int) : List<Shopitem>

    @Query("SELECT * FROM Shopitem ORDER By amount DESC LIMIT 1")
    fun getMostShop() : Shopitem

    @Query("SELECT * FROM Shopitem WHERE amount < :lessthan")
    fun getLessThanAmountShop(lessthan : Int) : List<Shopitem>

    @Query("SELECT * FROM Shopitem WHERE isbought = 1 AND amount = 1")
    fun getBoughtShopOne() : List<Shopitem>

    @Insert
    fun addShop(shopitem: Shopitem)

    @Delete
    fun deleteshop(shopitem: Shopitem)

    @Update
    fun updateShop(shopitem: Shopitem)
}

@Database(
    entities = [Shopitem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopitemDao(): ShopitemDao
}

