package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class CustomerService(
    val customerRepository : CustomerRepository,
    val bookService: BookService
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

    fun findById(id:Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException("Customer [${id}] not exists", "ML-0002") }
    }

    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun deleteCustomer(@PathVariable id:Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO;
        customerRepository.save(customer)
    }
}