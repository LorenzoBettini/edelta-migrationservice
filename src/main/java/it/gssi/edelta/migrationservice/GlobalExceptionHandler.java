package it.gssi.edelta.migrationservice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalExceptionHandler {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ErrorInfo handleMethodNotSupported(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		return new ErrorInfo(request, ex);
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleMultipartException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		return new ErrorInfo(request, "Invalid File Upload Request");
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorInfo handleIOException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		return new ErrorInfo(request, ex.getMessage());
	}
}
