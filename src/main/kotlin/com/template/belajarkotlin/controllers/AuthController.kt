package com.template.belajarkotlin.controllers


import com.template.belajarkotlin.models.dto.ApiException
import com.template.belajarkotlin.models.dto.LoginDto
import com.template.belajarkotlin.models.dto.LoginResponseDto
import com.template.belajarkotlin.models.dto.RegisterDto
import com.template.belajarkotlin.models.entity.User
import com.template.belajarkotlin.services.HashService
import com.template.belajarkotlin.services.TokenService
import com.template.belajarkotlin.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByName(payload.name) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(400, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.existsByName(payload.name)) {
            throw ApiException(400, "Name already exists")
        }

        val user = User(
            name = payload.name,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}