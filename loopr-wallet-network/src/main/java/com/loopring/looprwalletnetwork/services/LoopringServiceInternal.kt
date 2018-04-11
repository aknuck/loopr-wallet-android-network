package com.loopring.looprwalletnetwork.services

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.loopring.looprwalletnetwork.models.etherscan.eth.EthSupplyResponse
import com.loopring.looprwalletnetwork.models.ethplorer.eth.EthTokenInfo
import com.loopring.looprwalletnetwork.models.ethplorer.transactioninfo.EthAddressTransactions
import com.loopring.looprwalletnetwork.models.loopring.*
import com.loopring.looprwalletnetwork.utilities.DateDeserializer
import kotlinx.coroutines.experimental.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

open interface LoopringServiceInternal {
    /**
     * Wallet balance
     *
     * @return [LooprBalance]
     */
    @FormUrlEncoded
    @POST("/")
    fun getBalances(@Field("jsonrpc") jsonRpc: String,
                    @Field("method") method: String,
                    @Field("params") params: String,
                    @Field("id") id: String): Deferred<LooprBalance>

    /**
     * Submit an Order
     *
     * @return [LooprOrderResponse]
     */
    @FormUrlEncoded
    @POST("/")
    fun submitOrder(@Field("jsonrpc") jsonRpc: String,
                    @Field("method") method: String,
                    @Field("params") params: String,
                    @Field("id") id: String): Deferred<LooprOrderResponse>

    /**
     * Get loopring order list
     *
     * @return [LooprOrderList]
     */
    @FormUrlEncoded
    @POST("/")
    fun getOrderList(@Field("jsonrpc") jsonRpc: String,
                     @Field("method") method: String,
                     @Field("params") params: String,
                     @Field("id") id: String): Deferred<LooprOrderList>

    /**
     * Get depth and accuracy by token pair
     *
     * @return [LooprDepth]
     */
    @FormUrlEncoded
    @POST("/")
    fun getDepth(@Field("jsonrpc") jsonRpc: String,
                 @Field("method") method: String,
                 @Field("params") params: String,
                 @Field("id") id: String): Deferred<LooprDepth>

    /**
     * Get all market 24hr merged tickers info from loopring relay
     *
     * @return [LooprTickerList]
     */
    @FormUrlEncoded
    @POST("/")
    fun getTicker(@Field("jsonrpc") jsonRpc: String,
                 @Field("method") method: String,
                 @Field("params") params: String,
                 @Field("id") id: String): Deferred<LooprTickerList>

    /**
     * Get all market 24hr merged tickers info from loopring relay
     *
     * @return [LooprTickerList]
     */
    @FormUrlEncoded
    @POST("/")
    fun getTickers(@Field("jsonrpc") jsonRpc: String,
                  @Field("method") method: String,
                  @Field("params") params: String,
                  @Field("id") id: String): Deferred<LooprTickerExchangeList>


    companion object {

        @Suppress("MemberVisibilityCanBePrivate")

        private const val BASE_URL = "http://13.112.62.24/rpc/v2/"//"https://relay1.loopring.io/rpc/"

        /**
         * Get a Retrofit reference to use for calling the Etherscan API functions
         *
         * @return [Retrofit] object configured with the necessary custom deserializers and built with the Etherscan base URL
         */
        fun getService(): LoopringServiceInternal {

            val httpClient = OkHttpClient()
            /*httpClient.interceptors().add(Interceptor {
                val request = it.request()

                val httpUrl = it.request().url().newBuilder()
                        .build()

                val newRequest = request.newBuilder().url(httpUrl).build()
                it.proceed(newRequest)
            })*/

            val gson = GsonBuilder()
                    .registerTypeAdapter(Date::class.java, DateDeserializer())
                    .enableComplexMapKeySerialization()
                    .serializeNulls()
                    .create()

            val retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(LoopringServiceInternal.BASE_URL)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create<LoopringServiceInternal>(LoopringServiceInternal::class.java)
        }
    }

}