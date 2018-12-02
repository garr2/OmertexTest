package com.garr.pavelbobrovko.omertextest.inject

import android.content.Context
import com.garr.pavelbobrovko.data.net.ImageService
import com.garr.pavelbobrovko.data.net.TextInfoService
import com.garr.pavelbobrovko.data.repositories.ManRepositoryImpl
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.ManRepository
import com.garr.pavelbobrovko.omertextest.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext() : Context  = context

    @Provides
    fun provideManRepository(textInfoService: TextInfoService, imageService: ImageService)
            : ManRepository
            = ManRepositoryImpl(textInfoService, imageService)

    @Provides
    fun provideTextInfoService(@Named(URL_INJECT_DATA_SERVER) serverUrl: String) : TextInfoService
            = TextInfoService(serverUrl)

    @Provides
    fun provideImageService(@Named(URL_INJECT_IMAGE_SERVER) serverUrl: String) : ImageService
            = ImageService(serverUrl)

    @Provides
    fun providePostExecutorThread() : PostExecutorThread
            = UIThread()

    @Provides
    @Named(URL_INJECT_DATA_SERVER)
    fun provideDataServerUrl() : String  = "https://jsonplaceholder.typicode.com/posts/1/"

    @Provides
    @Named(URL_INJECT_IMAGE_SERVER)
    fun provideImageServerUrl() : String  = "https://api.flickr.com/services/"
}