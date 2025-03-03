package se.magictechnology.pia13android3mar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {

    lateinit var appdatabase : AppDatabase

    private val _allshoping = MutableStateFlow(listOf<Shopitem>())
    val allshopping: StateFlow<List<Shopitem>> = _allshoping.asStateFlow()


    fun loadshopping() {
        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getAllShop()

            _allshoping.value = loadshop
        }

    }

    fun addshop(shoptitle : String, shopamount : Int) {
        var newshop = Shopitem( uid = 0, shopname = shoptitle, amount =  shopamount, isbought = false)

        CoroutineScope(Dispatchers.IO).launch {
            appdatabase.shopitemDao().addShop(newshop)
            loadshopping()
        }
    }

    fun deleteshop(shopitem : Shopitem) {
        CoroutineScope(Dispatchers.IO).launch {
            appdatabase.shopitemDao().deleteshop(shopitem)
            loadshopping()
        }

    }

}