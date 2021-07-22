package br.com.zupacademy.felipe.gadelha.proposta.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		var status = HttpStatus.resolve(response.status());
		return FeignStatus.convert(status).exceptionHandler(response);
	}
}
