package com.mercadolivro.controller

import com.mercadolivro.controller.apidoc.BookControllerOpenApi
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
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
) : BookControllerOpenApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun create(@RequestBody @Valid request: PostBookRequest) {
        var customer = customerService.findById(request.customerId)
        bookService.createBook(request.toBookModel(customer))
    }

    @GetMapping
    override fun getAll(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookResponse> {
        return bookService.getAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    override fun getActives(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookResponse> =
        bookService.findActives(pageable).map { it.toResponse() }


    @GetMapping("/{id}")
    override fun getById(@PathVariable id:Int): BookResponse {
        return bookService.findbyId(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateById(@PathVariable id:Int, @RequestBody customer: PutBookRequest) {
        val book = bookService.findbyId(id)
        bookService.updateBook(customer.toBookModel(book))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteById(@PathVariable id:Int) {
        bookService.deleteBook(id)
    }
}