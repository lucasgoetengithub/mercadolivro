package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService(
    val customerRepository : CustomerRepository
    ) {

    fun getAll(nome: String?): List<CustomerModel> {
        nome?.let {
            return customerRepository.findByNomeContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun createCustomer(customer: CustomerModel){
        customerRepository.save(customer)
    }

    fun getCustomer(id:Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(@PathVariable id:Int) {
        customerRepository.deleteById(id)
    }
}