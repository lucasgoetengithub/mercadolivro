package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(nome = this.nome, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, nome = this.nome, email = this.email)
}

fun PostBookRequest.toBookModel(customer : CustomerModel):BookModel{
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer_id = customer
    )
}

fun PutBookRequest.toBookModel(book : BookModel):BookModel{
    return BookModel(
        id = book.id,
        name = this.name?: book.name,
        price = this.price?: book.price,
        status = book.status,
        customer_id = book.customer_id
    )
}