package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookReponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
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
    fun getAll() : List<BookReponse> {
        return bookService.getAll().map { it.toResponse() }
    }

    @GetMapping("/active")
    fun getActive() : List<BookReponse> =
        bookService.findActives().map { it.toResponse() }


    @GetMapping("/{id}")
    fun getById(@PathVariable id:Int): BookReponse {
        return bookService.getByIdBook(id).toResponse()
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