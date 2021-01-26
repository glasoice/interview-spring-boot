package com.echevarne.sap.cloud.interview.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class CrudProductos {
	
	@GetMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String getProducto() {
		return "Healthy Get";
	}
	
	@PutMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String putProducto() {
		return "Healthy Put";
	}
	
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String postProducto() {
		return "Healthy Post";
	}
	
	@DeleteMapping(value = "/", consumes = "application/json", produces = "application/json")
	public String deleteProducto() {
		return "Healthy Delete";
	}
	
}
