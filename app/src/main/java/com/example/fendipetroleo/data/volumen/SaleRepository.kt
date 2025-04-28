package com.example.fendipetroleo.data.volumen

class SaleRepository(private val saleDao: SaleDao) {

    fun getAllSales() = saleDao.getAllSales()
    suspend fun addSale(sale: Sale) = saleDao.addSale(sale)
    suspend fun getSaleById(id: Int) = saleDao

}