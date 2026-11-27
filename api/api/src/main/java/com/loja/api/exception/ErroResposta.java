package com.loja.api.excepcion; 

import java.time.LocalDateTime;
import java.util.List;

public record ErroResposta(int status, String erro, String mensagem, List<CampoErro> campos, LocalDateTime timestamp) {}
