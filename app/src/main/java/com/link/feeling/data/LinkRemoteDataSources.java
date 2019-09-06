package com.link.feeling.data;

import com.link.feeling.data.network.LinkApi;

import retrofit2.Retrofit;

/**
 * Created on 2019/1/17  11:28
 * chenpan pan.chen@linkfeeling.cn
 */
public final class LinkRemoteDataSources implements LinkDataSources {

    private final LinkApi mApi;

    LinkRemoteDataSources(Retrofit retrofit) {
        mApi = retrofit.create(LinkApi.class);
    }

}

