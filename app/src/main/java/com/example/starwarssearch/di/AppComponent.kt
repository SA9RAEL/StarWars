package com.example.starwarssearch.di

import android.content.Context
import com.example.starwarssearch.presentation.MainActivity
import com.example.starwarssearch.presentation.fragments.CharactersDetailsFragment
import com.example.starwarssearch.presentation.fragments.HomeFragment
import com.example.starwarssearch.presentation.fragments.PlanetsDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder{
        fun withContext(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: PlanetsDetailsFragment)
    fun inject(fragment: CharactersDetailsFragment)

}