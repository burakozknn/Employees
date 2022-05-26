package com.example.burakozknn.employeedirectory.api

import android.annotation.SuppressLint
import com.example.burakozknn.employeedirectory.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ApiService {

    private val client = getOkHttpClient()

    //Setting retrofit configurations
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    //Creating a generic method to build service
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    //Creating a trust manager to be able to monitor request/response in Proxyman/Charles
    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()

        val sslContext = SSLContext.getInstance("TLS")
        // Allows all certificate chains
        val trustAllX509: X509TrustManager = object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                //do nothing
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                //do nothing
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            trustAllX509
        )
        sslContext.init(null, trustAllCerts, SecureRandom())
        okHttpClientBuilder.sslSocketFactory(sslContext.socketFactory, trustAllX509)

        // Allows mismatched hostnames on the certificate
        val allowAllHostsHostnameVerifier =
            HostnameVerifier { hostname: String?, session: SSLSession? ->
                true
            }
        okHttpClientBuilder.hostnameVerifier(allowAllHostsHostnameVerifier)

        return okHttpClientBuilder.build()
    }
}