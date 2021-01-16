package br.com.hackaton.ccr.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.hackaton.ccr.exceptions.AppException;
import lombok.Data;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> handleResourceNotFoundException(AppException exception, HttpServletRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimestamp(LocalDateTime.now());
		errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetail.setError(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorDetail.setMessage(exception.getMessage());
		errorDetail.setPath(request.getRequestURI());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@Data
class ErrorDetail {

	private LocalDateTime timestamp;

	private int status;

	private String error;

	private String message;

	private String path;

}
