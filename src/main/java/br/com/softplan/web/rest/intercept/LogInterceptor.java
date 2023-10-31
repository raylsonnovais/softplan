package br.com.softplan.web.rest.intercept;

import br.com.softplan.domain.LogDeAcao;

import br.com.softplan.domain.Usuario;
import br.com.softplan.service.LogDeAcaoService;
import br.com.softplan.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LogDeAcaoService logDeAcaoService;
    private final UsuarioService usuarioService;

    @Autowired
    public LogInterceptor(LogDeAcaoService logDeAcaoService, UsuarioService usuarioService) {
        this.logDeAcaoService = logDeAcaoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {

            String metodoRequisicao = request.getMethod();


            String usuarioNome = request.getRemoteUser();
            String detalhes = "DETALHES DA AÇÃO";
            String ip = request.getRemoteAddr();

            Usuario usuario = usuarioService.obterUsuarioPorNome(usuarioNome);
            LogDeAcao logDeAcao = new LogDeAcao();
            logDeAcao.setUsuario(usuario);
            logDeAcao.setAcao(metodoRequisicao);
            logDeAcao.setDetalhes(detalhes);
            logDeAcao.setDataHora(LocalDateTime.now());
            logDeAcao.setIp(ip);


            logDeAcaoService.save(logDeAcao);
        } catch (Exception e) {
            throw e;
        }

        return true;
    }
}

