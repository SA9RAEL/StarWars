package com.example.starwarssearch.presentation.mapper

import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.presentation.models.CharacterPresentation

object CharacterMapper {
    fun map(character: Character): CharacterPresentation =
        with(character) {
            CharacterPresentation(
                id = character.id,
                birthYear = character.birthYear,
                eyeColor = character.eyeColor,
                films = character.films,
                gender = character.gender,
                hairColor = character.hairColor,
                height = character.height,
                homeWorld = character.homeWorld,
                mass  = character.mass,
                name  = character.name,
                skinColor = character.skinColor

            )
        }
}