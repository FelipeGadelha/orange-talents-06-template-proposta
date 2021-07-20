package br.com.zupacademy.felipe.gadelha.proposta.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum FeignStatus {
	NOT_FOUND {
		@Override
		Exception exceptionHandler() {
			return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
		}
	},
	UNPROCESSABLE_ENTITY {
		@Override
		Exception exceptionHandler() {
			return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível processar a requisição");
		}
	},
	BAD_REQUEST {
		@Override
		Exception exceptionHandler() {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos");
		}
	},
	FORBIDDEN {
		@Override
		Exception exceptionHandler() {
			return new ResponseStatusException(HttpStatus.FORBIDDEN, "Recurso com acesso restrito");
		}
	},
	INTERNAL_SERVER_ERROR {
		@Override
		Exception exceptionHandler() {
			return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
		}
	}
	;
	static FeignStatus convert(HttpStatus status) {
		return FeignStatus.valueOf(status.name());
	}
	abstract Exception exceptionHandler();
}
