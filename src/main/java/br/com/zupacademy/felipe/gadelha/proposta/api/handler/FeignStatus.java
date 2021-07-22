package br.com.zupacademy.felipe.gadelha.proposta.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;

public enum FeignStatus {
	NOT_FOUND {
		@Override
		Exception exceptionHandler(Response response) {
			return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado " + response.request());
		}
	},
	UNPROCESSABLE_ENTITY {
		@Override
		Exception exceptionHandler(Response response) {
			return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível processar a requisição " + response.request());
		}
	},
	BAD_REQUEST {
		@Override
		Exception exceptionHandler(Response response) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos " + response.request());
		}
	},
	FORBIDDEN {
		@Override
		Exception exceptionHandler(Response response) {
			return new ResponseStatusException(HttpStatus.FORBIDDEN, "Recurso com acesso restrito " + response.request());
		}
	},
	INTERNAL_SERVER_ERROR {
		@Override
		Exception exceptionHandler(Response response) {
			return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor " + response.request());
		}
	}
	;
	static FeignStatus convert(HttpStatus status) {
		return FeignStatus.valueOf(status.name());
	}
	abstract Exception exceptionHandler(Response response);
}
