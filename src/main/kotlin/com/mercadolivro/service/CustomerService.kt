package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(nome: String?): List<CustomerModel> {
        nome?.let {
            return customers.filter { it.nome.contains(nome, ignoreCase = true)}
        }
        return customers
    }

    fun createCustomer(customer: PostCustomerRequest){
        val id = if (customers.isEmpty()){
            1
        } else {
            customers.last().id.toInt() +1
        }.toString()

        customers.add(CustomerModel(id, customer.nome, customer.email))
        println(customer)
    }

    fun getCustomer(id:String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun updateCustomer(id:String, customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.nome = customer.nome
            it.email= customer.email
        }
    }

    fun deleteCustomer(@PathVariable id:String) {
        customers.removeIf { it.id == id }
    }
}