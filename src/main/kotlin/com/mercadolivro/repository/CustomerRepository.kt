package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    fun findByNomeContaining(nome:String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
}