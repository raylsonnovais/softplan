package br.com.softplan.configuration;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoConfig {
    @Value("${mercadopago.access-token}")
    private String accessToken;

    @PostConstruct
    public void init() throws MPConfException {
        MercadoPago.SDK.setAccessToken(accessToken);
    }

    @Bean
    public MercadoPago mercadoPagoClient() {
        return new MercadoPago();
    }
}
