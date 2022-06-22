package com.mercadolivro.controller.response

import com.mercadolivro.enums.CustomerStatus
import javax.persistence.*

data class CustomerReponse (
    var id: Int? = null,
    var nome: String,
    var email: String,
    var status: CustomerStatus
        )
