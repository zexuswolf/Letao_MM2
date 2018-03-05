package com.letao.mm2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letao.mm2.model.MaterialType;
import com.letao.mm2.repository.MaterialTypeRepository;

@RestController
@RequestMapping("/api/materialTypes")
public class MaterialTypeController {

	@Autowired
	MaterialTypeRepository materialTypeRepository;
	
	@GetMapping
	public ResponseEntity<?> listAllMaterialTypes(){
		List<MaterialType> types = materialTypeRepository.listAllMaterialTypes();
		return new ResponseEntity(types,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findMaterialTypeById(@PathVariable long id){
		MaterialType type = materialTypeRepository.findOne(id);
		return new ResponseEntity(type,HttpStatus.OK);
	}
	
}
