package di

import org.koin.dsl.module
import repos.HeroRepository
import repos.HeroRepositoryImpl

val koinModule = module{
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}