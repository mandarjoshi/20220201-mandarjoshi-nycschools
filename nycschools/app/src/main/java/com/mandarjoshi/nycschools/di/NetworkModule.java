package com.mandarjoshi.nycschools.di;

import com.mandarjoshi.nycschools.BuildConfig;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetworkModule {

    @Provides
    public Retrofit providesRetrofit(MoshiConverterFactory moshiConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .build();
    }

    @Provides
    public Moshi providesMoshi(){
        return new Moshi.Builder().build();
    }

    @Provides
    public MoshiConverterFactory providesMoshiConverterFactory(Moshi moshi){
        return MoshiConverterFactory.create(moshi);
    }
}
