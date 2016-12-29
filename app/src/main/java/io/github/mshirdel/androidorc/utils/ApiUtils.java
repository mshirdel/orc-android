package io.github.mshirdel.androidorc.utils;

import io.github.mshirdel.androidorc.Service.OrcService;
import io.github.mshirdel.androidorc.Service.RetrofitClient;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.1.2";

    public static OrcService getOrcService(){
        return RetrofitClient.getClient(BASE_URL).create(OrcService.class);
    }
}
