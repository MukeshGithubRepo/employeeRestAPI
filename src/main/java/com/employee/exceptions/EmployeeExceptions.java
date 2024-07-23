package com.employee.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptions {
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidData(MethodArgumentNotValidException exc)
	{
		Map<String, String> errorMap=new HashMap<>();
		
		exc.getBindingResult().getFieldErrors().forEach(err->errorMap.put(err.getField(), err.getDefaultMessage()));
		return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
	}

}
