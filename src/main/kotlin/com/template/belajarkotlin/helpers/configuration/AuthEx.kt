package com.template.belajarkotlin.helpers.configuration

import com.template.belajarkotlin.models.entity.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}