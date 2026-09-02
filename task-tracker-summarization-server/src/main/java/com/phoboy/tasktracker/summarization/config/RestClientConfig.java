package com.phoboy.tasktracker.summarization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestFactory(createInsecureRequestFactory())
                .build();
    }

    private SimpleClientHttpRequestFactory createInsecureRequestFactory() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            return factory;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось настроить SSL-игнорирование", e);
        }
    }
}

