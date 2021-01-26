package com.echevarne.sap.cloud.interview.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class CrudClientes {
	
	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String getCliente() {
		return "Healthy Get";
	}
	
	@PutMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String putCliente() {
		return "Healthy Put";
	}
	
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String postCliente() {
		return "Healthy Post";
	}
	
	@DeleteMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String deleteCliente() {
		return "Healthy Delete";
	}
	
}
