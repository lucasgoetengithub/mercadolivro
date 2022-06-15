package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel, Int>{
    abstract fun findByStatus(Status:BookStatus): List<BookModel>
    abstract fun findByCustomer(customer: CustomerModel): List<BookModel>

}