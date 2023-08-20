package com.example.starwarssearch.presentation.mapper

import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.models.Planet
import com.example.starwarssearch.presentation.models.CharacterPresentation
import com.example.starwarssearch.presentation.models.PlanetPresentation

class PlanetMapper : (Planet) -> PlanetPresentation {
    override fun invoke(planet: Planet): PlanetPresentation =
        with(planet) {
            PlanetPresentation(
                id = planet.id,
                climate = planet.climate,
                created = planet.created,
                diameter = planet.diameter,
                edited = planet.edited,
                films = planet.films,
                gravity = planet.gravity,
                name = planet.name,
                orbitalPeriod = planet.orbitalPeriod,
                population = planet.population,
                residents = planet.residents,
                rotationPeriod = planet.rotationPeriod,
                surfaceWater = planet.surfaceWater,
                terrain = planet.terrain,
                url = planet.url
            )
        }
}
