package com.template.belajarkotlin.models.repository

import com.template.belajarkotlin.models.entity.Item
import com.template.belajarkotlin.models.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepo: CrudRepository<Item, Long> {
    fun findByUser(user: User): List<Item>
    fun findByNameAndUser(name: String, user: User): Item?
    fun existsByNameAndUser(name: String, user: User): Boolean
}