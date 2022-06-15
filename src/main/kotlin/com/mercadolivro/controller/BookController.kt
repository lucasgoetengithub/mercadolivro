package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody request: PostBookRequest) {
        var customer = customerService.findById(request.customerId)
        bookService.createBook(request.toBookModel(customer))
    }

    @GetMapping
    fun getAll() : List<BookModel> {
        return bookService.getAll()
    }

    @GetMapping("/active")
    fun getActive() : List<BookModel> =
        bookService.findActives()


    @GetMapping("/{id}")
    fun getById(@PathVariable id:Int): BookModel {
        return bookService.getByIdBook(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id:Int, @RequestBody customer: PutBookRequest) {
        val book = bookService.getByIdBook(id)
        bookService.updateBook(customer.toBookModel(book))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id:Int) {
        bookService.deleteBook(id)
    }
}