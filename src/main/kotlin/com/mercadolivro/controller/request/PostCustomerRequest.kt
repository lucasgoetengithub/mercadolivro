package com.mercadolivro.controller.request

import com.mercadolivro.model.CustomerModel
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(

        @field:NotEmpty
        var nome: String,

        @field:Email
        var email: String)