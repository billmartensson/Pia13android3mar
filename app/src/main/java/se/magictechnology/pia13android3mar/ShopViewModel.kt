package se.magictechnology.pia13android3mar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {

    var isPreview = false

    lateinit var appdatabase : AppDatabase

    private val _allshopping = MutableStateFlow(listOf<Shopitem>())
    val allshopping: StateFlow<List<Shopitem>> = _allshopping.asStateFlow()

    private val _doneshopping = MutableStateFlow(listOf<Shopitem>())
    val doneshopping: StateFlow<List<Shopitem>> = _doneshopping.asStateFlow()

    private val _navbartitle = MutableStateFlow("START")
    val navbartitle = _navbartitle.asStateFlow()

    fun setNavbarTitle(title : String) {
        _navbartitle.value = title
    }

    fun loadshopping() {
        if(isPreview) {
            _allshopping.value = makePreviewData()
            return
        }


        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getAllShop()

            _allshopping.value = loadshop
        }

    }

    fun loadBoughtShopping() {
        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getBoughtShop()

            _allshopping.value = loadshop
        }

    }

    fun loadNotBoughtShopping() {
        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getNotBoughtShop()

            _allshopping.value = loadshop
        }

    }

    fun loadDoneShopping() {
        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getBoughtShop()

            _doneshopping.value = loadshop
        }

    }


    fun loadMoreThanShopping(morethanamount : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var loadshop = appdatabase.shopitemDao().getMoreThanAmountShop(morethanamount)

            _allshopping.value = loadshop
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

    fun changeBought(shopitem: Shopitem) {
        shopitem.isbought = !shopitem.isbought

        CoroutineScope(Dispatchers.IO).launch {
            appdatabase.shopitemDao().updateShop(shopitem)
            _allshopping.value = mutableListOf()
            loadshopping()
        }
    }



    fun makePreviewData() : List<Shopitem> {
        var previewShop = mutableListOf<Shopitem>()
        var shop1 = Shopitem(uid = 0, shopname = "Apelsin", amount = 10, isbought = false)
        var shop2 = Shopitem(uid = 0, shopname = "Banan", amount = 2, isbought = true)
        previewShop.add(shop1)
        previewShop.add(shop2)

        return previewShop
    }

}

// Fixa 1
// TODO: Fixa viewmodel
//FIXME: abc