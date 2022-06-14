package com.mercadolivro.service

import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(
    val bookRepository: BookRepository 
) {
    fun getAll(nome: String?): List<BookModel> {
        
        return bookRepository.findAll().toList()
    }

    fun createBook(book: BookModel){
        bookRepository.save(book)
    }

    fun getByIdBook(id:Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun updateBook(Book: BookModel) {
        if (!bookRepository.existsById(Book.id!!)) {
            throw Exception()
        }
        bookRepository.save(Book)
    }

    fun deleteBook(@PathVariable id:Int) {
        bookRepository.deleteById(id)
    }
}
