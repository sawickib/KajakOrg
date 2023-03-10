package com.mobeedev.kajakorg.ui.di

import com.mobeedev.kajakorg.data.KayakPathRepositoryImpl
import com.mobeedev.kajakorg.data.datasource.local.LocalPathSource
import com.mobeedev.kajakorg.data.datasource.remote.RemotePathSource
import com.mobeedev.kajakorg.domain.repository.KayakPathRepository
import com.mobeedev.kajakorg.domain.usecase.*
import com.mobeedev.kajakorg.ui.MainViewModel
import com.mobeedev.kajakorg.ui.common.ModuleLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


object MainModule : ModuleLoader() {

    override val modules: List<Module> =
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )

}

private val viewModelModule = module {
    viewModel {
        MainViewModel(
            application = get(),
            loadAllAvailablePathsUseCase = get(),
            loadAllPathsDetailsUseCase = get(),
            getPathsOverviewUseCase = get(),
            getPathsDetailsUseCase = get(),
            getLastUpdateDateUseCase = get()
        )
    }
}

private val useCaseModule = module {
    factory { LoadAllAvailablePathsUseCase(kayakPathRepository = get()) }
    factory { LoadAllPathsDetailsUseCase(kayakPathRepository = get()) }

    factory { GetPathsOverviewUseCase(kayakPathRepository = get()) }
    factory { GetPathsDetailsUseCase(kayakPathRepository = get()) }
    factory { GetLastUpdateDateUseCase(kayakPathRepository = get()) }
}

private val repositoryModule = module {
    single<KayakPathRepository> {
        KayakPathRepositoryImpl(
            remotePathSource = get(),
            localPathSource = get(),
            context = get()

        )
    }
}

private val dataSourceModule = module {
    single { LocalPathSource(kajakPathDao = get()) }
    single { RemotePathSource(kajakOrgService = get()) }
}