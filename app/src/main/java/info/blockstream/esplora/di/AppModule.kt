package info.blockstream.esplora.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import info.blockstream.esplora.data.remote.DemoRemoteDataSource
import info.blockstream.esplora.data.remote.DemoService
import info.blockstream.esplora.data.repository.DemoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://blockstream.info/testnet/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideDemoService(retrofit: Retrofit): DemoService =
        retrofit.create(DemoService::class.java)

    @Singleton
    @Provides
    fun provideDemoRemoteDataSource(demoService: DemoService) = DemoRemoteDataSource(demoService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: DemoRemoteDataSource
    ) =
        DemoRepository(remoteDataSource)
}