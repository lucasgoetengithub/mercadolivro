package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookReponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid request: PostBookRequest) {
        var customer = customerService.findById(request.customerId)
        bookService.createBook(request.toBookModel(customer))
    }

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookReponse> {
        return bookService.getAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun getActive(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookReponse> =
        bookService.findActives(pageable).map { it.toResponse() }


    @GetMapping("/{id}")
    fun getById(@PathVariable id:Int): BookReponse {
        return bookService.findbyId(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id:Int, @RequestBody customer: PutBookRequest) {
        val book = bookService.findbyId(id)
        bookService.updateBook(customer.toBookModel(book))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id:Int) {
        bookService.deleteBook(id)
    }
}