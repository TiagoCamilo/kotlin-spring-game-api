package com.braveplayers.tibia.characters

import com.braveplayers.tibia.characters.controllers.CharacterController
import com.braveplayers.tibia.characters.dtos.CharacterDto
import com.braveplayers.tibia.characters.services.CharacterService
import com.braveplayers.tibia.characters.util.Mapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import com.braveplayers.tibia.characters.entities.Character as Character

@SpringBootTest
class CharacterControllerTest {

    @Autowired
    lateinit var controller: CharacterController

    @MockBean
    lateinit var service: CharacterService

    @Test
    fun findByIdShouldReturnCharacter() {
        val id = 1L
        val dto = CharacterDto("char1", 100)
        val entity = Character(id, "char1", 100)

        BDDMockito.given(service.findById(id)).willReturn(entity)

        Assertions.assertEquals(dto, controller.findById(id))
    }

    @Test
    fun createShouldCreateAndReturnCharacter() {
        val dto = CharacterDto("char1", 100)
        val entity: Character  = Mapper.convert(dto)

        BDDMockito.given(service.create(entity)).willReturn(entity)

        Assertions.assertEquals(dto, controller.create(dto))
    }


}