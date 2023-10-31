package br.com.softplan.client;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoPaymentClient {

    public Payment createPayment(Payment payment) throws MPException {
        return payment.save();
    }

    public Payment getPaymentById(String paymentId) throws MPException {
        return Payment.findById(paymentId);
    }


    public Payment refundPayment(String paymentId) throws MPException {
        Payment payment = Payment.findById(paymentId);
        payment.refund();
        return payment;
    }
}
