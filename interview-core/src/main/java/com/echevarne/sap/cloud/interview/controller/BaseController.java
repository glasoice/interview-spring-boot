package com.echevarne.sap.cloud.interview.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("interview")
public class BaseController {
	
	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String getExample() {
		return "Healthy Get";
	}
	
	@PutMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String putExample() {
		return "Healthy Put";
	}
	
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String postExample() {
		return "Healthy Post";
	}
	
	@DeleteMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String deleteExample() {
		return "Healthy Delete";
	}
	
}
