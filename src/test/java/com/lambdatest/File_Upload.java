package com.lambdatest;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class File_Upload {
    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");

    public static void upload_files() throws IOException {

        String credential = Credentials.basic(userName, accessKey);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("files","files_prj/test.csv",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("files_prj/test.csv")))
                .build();
        Request request = new Request.Builder()
                .url("https://api.lambdatest.com/automation/api/v1/user-files")
                .method("POST", body)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", credential)
                .build();
        Response response = client.newCall(request).execute();

//        System.out.println(response.body().string());
    }
}

