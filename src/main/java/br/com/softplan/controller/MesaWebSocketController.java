package br.com.softplan.controller;

import br.com.softplan.domain.Mesa;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MesaWebSocketController {

    @MessageMapping("/notificarMesaVaga")
    @SendTo("/topic/mesaVaga")
    public String notificarMesaVaga(Mesa mesa) {

        String mensagem = "Mesa " + mesa.getId() + "da Filial "+ mesa.getFilial().getNome()+ " está vaga!";
        return mensagem;
    }
}

