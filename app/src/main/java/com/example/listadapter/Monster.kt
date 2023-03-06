package com.example.listadapter

data class Monster(
    val name: String,
    val race: Race,
    val level: Int,
    val stats: List<Int>,
    val encount: Boolean
)

enum class Race {
    Zombie, Human, Goblin, Dragon
}