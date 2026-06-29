package com.ecommerce.product.exception;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	private OffsetDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private List<String> errors;
	private String traceId;
}