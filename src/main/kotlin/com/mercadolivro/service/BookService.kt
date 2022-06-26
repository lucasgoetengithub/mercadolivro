package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(
    val bookRepository: BookRepository 
) {
    fun getAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun createBook(book: BookModel){
        bookRepository.save(book)
    }

    fun findbyId(id:Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun updateBook(Book: BookModel) {
        if (!bookRepository.existsById(Book.id!!)) {
            throw Exception()
        }
        bookRepository.save(Book)
    }

    fun deleteBook(@PathVariable id:Int) {
        val book = findbyId(id)
        book.status = BookStatus.CANCELADO
        bookRepository.save(book)
    }

    fun findActives(pageable : Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books =  bookRepository.findByCustomer(customer)
        for(book in books){
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books);
    }
}
