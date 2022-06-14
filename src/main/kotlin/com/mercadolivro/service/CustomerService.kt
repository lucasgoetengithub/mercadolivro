package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(nome: String?): List<CustomerModel> {
        nome?.let {
            return customers.filter { it.nome.contains(nome, ignoreCase = true)}
        }
        return customers
    }

    fun createCustomer(customer: CustomerModel){
        val id = if (customers.isEmpty()){
            1
        } else {
            customers.last().id!!.toInt() +1
        }.toString()

        customer.id = id;
        customers.add(customer)
        println(customer)
    }

    fun getCustomer(id:String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun updateCustomer(customer: CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.nome = customer.nome
            it.email= customer.email
        }
    }

    fun deleteCustomer(@PathVariable id:String) {
        customers.removeIf { it.id == id }
    }
}