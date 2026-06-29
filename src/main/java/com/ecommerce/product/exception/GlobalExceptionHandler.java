package com.ecommerce.product.exception;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex,
			HttpServletRequest request) {

		log.error("Resource not found : {}", ex.getMessage());

		return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request) {

		log.warn("Bad Request : {}", ex.getMessage());

		return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {

		log.warn("Duplicate Resource : {}", ex.getMessage());

		return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage).toList();

		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", request.getRequestURI(),
				validationErrors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

		log.error("Unexpected Exception", ex);

		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", request.getRequestURI(),
				null);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, String path,
			List<String> errors) {

		ErrorResponse response = ErrorResponse.builder().timestamp(OffsetDateTime.now()).status(status.value())
				.error(status.getReasonPhrase()).message(message).path(path)
				.errors(errors == null ? Collections.emptyList() : errors).traceId(null).build();

		return ResponseEntity.status(status).body(response);
	}
}