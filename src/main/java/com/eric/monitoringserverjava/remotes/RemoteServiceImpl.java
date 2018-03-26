package com.eric.monitoringserverjava.remotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
@Service
public class RemoteServiceImpl implements RemoteService {
    private static Logger LOGGER = LoggerFactory.getLogger(RemoteServiceImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public RemoteServiceImpl (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void init () {
        disableCertificateVerification();
    }

    private void disableCertificateVerification () {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers () {
                return null;
            }

            @Override
            public void checkClientTrusted (java.security.cert.X509Certificate[] arg0, String arg1) {
            }

            @Override
            public void checkServerTrusted (java.security.cert.X509Certificate[] arg0, String arg1) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                    (hostname, session) -> hostname.equals("IPADDRESS"));
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public ResponseEntity<String> sendGetRequest (URI url) {
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        LOGGER.debug(
                "Retrieved {} from endpoint {} with http status {}.",
                entity.getBody(),
                url,
                entity.getStatusCode()
        );

        return entity;
    }
}
