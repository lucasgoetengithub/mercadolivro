package com.mercadolivro.controller.apidoc

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Book", description = "this API provide methods for operations with Books")
interface BookControllerOpenApi {

    @Operation(summary = "Create new book")
    fun create(request: PostBookRequest)

    @Operation(summary = "Find all new books, also all book containing text on title")
    fun getAll(pageable : Pageable) : Page<BookResponse>

    @Operation(summary = "Find all books in active status")
    fun getActives(pageable : Pageable) : Page<BookResponse>

    @Operation(summary = "Find a book by id")
    fun getById(id: Int): BookResponse

    @Operation(summary = "Delete a book by id")
    fun deleteById(id: Int)

    @Operation(summary = "Update book data by id")
    fun updateById(id:Int, customer: PutBookRequest)
}