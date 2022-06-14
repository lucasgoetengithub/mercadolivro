package com.mercadolivro.repository

import com.mercadolivro.model.BookModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel, Int>{

}