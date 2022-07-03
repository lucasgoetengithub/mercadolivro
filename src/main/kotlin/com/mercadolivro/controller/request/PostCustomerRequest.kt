package com.mercadolivro.controller.request

import com.mercadolivro.model.CustomerModel
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(

        @field:NotEmpty(message = "Nome deve ser informado.")
        var nome: String,

        @field:Email(message = "E-mail deve ser válido.")
        var email: String)