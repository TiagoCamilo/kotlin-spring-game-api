package com.braveplayers.game.entities

import javax.persistence.*

@Entity
@Table(name = "character")
data class Character(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        val level: Int = 0,

        @ManyToOne
        var guild: Guild? = null
)