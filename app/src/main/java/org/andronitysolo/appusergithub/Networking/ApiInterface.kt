package org.andronitysolo.appusergithub.Networking

import io.reactivex.Flowable
import org.andronitysolo.appusergithub.Response.ResponseUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @GET("/users/{username}")
    @Headers("Authentication: token <55eb62b5dacf3935ede0e3c71e2fc1a1add59bb8>")
     fun findUserDetailByUsername(
        @Path("username") username: String
    ):Flowable<Response<ResponseUser>>

    @GET("/search/users")
    @Headers("Authentication:token <55eb62b5dacf3935ede0e3c71e2fc1a1add59bb8>")
    fun findUserSearchByUsername(
        @Query("q") username: String
    ):Flowable<Response<ResponseUser>>

    @GET("/users/{username}/followers")
    @Headers("Authentication: token <55eb62b5dacf3935ede0e3c71e2fc1a1add59bb8>")
     fun findUserFollowersByUsername(
        @Path("username") username: String
    ):Flowable<Response<ResponseUser>>

    @GET("/users/{username}/following")
    @Headers("Authentication: token <55eb62b5dacf3935ede0e3c71e2fc1a1add59bb8>")
     fun findUserFollowingByUsername(
        @Path("username") username: String
    ):Flowable<Response<ResponseUser>>


}