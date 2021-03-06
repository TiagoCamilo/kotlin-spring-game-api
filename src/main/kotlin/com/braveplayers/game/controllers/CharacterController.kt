package com.braveplayers.game.controllers

import com.braveplayers.game.dtos.CharacterDto
import com.braveplayers.game.entities.Character
import com.braveplayers.game.services.CharacterService
import com.braveplayers.game.util.Mapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/characters")
class CharacterController(private val service: CharacterService) {

    @PostMapping
    fun createOrUpdate(@Valid @RequestBody dto: CharacterDto): ResponseEntity<CharacterDto> {
        val mappedEntity: Character = Mapper.convert(dto)
        val createdEntity: CharacterDto = Mapper.convert(service.createOrUpdate(mappedEntity))

        return ResponseEntity<CharacterDto>(createdEntity, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CharacterDto> {
        val foundEntity: CharacterDto = Mapper.convert(service.findById(id))

        return ResponseEntity<CharacterDto>(foundEntity, HttpStatus.OK)
    }

    @GetMapping()
    fun findAll(): ResponseEntity<Collection<CharacterDto>> {
        val entityCollection = service.findAll()
        val dtoCollection: Collection<CharacterDto> = entityCollection.map { Mapper.convert(it) }

        return ResponseEntity<Collection<CharacterDto>>(dtoCollection, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<CharacterDto> {
        val deletedEntity: CharacterDto = Mapper.convert(service.delete(id))

        return ResponseEntity<CharacterDto>(deletedEntity, HttpStatus.OK)
    }

}