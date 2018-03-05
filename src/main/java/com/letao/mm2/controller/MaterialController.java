package com.letao.mm2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.letao.mm2.model.Material;
import com.letao.mm2.model.MaterialType;
import com.letao.mm2.repository.MaterialRepository;
import com.letao.mm2.repository.MaterialTypeRepository;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private MaterialTypeRepository materialTypeRepository;
	
	@GetMapping
	public ResponseEntity<?> listAllMaterials(){
		List<Material> mats = materialRepository.listAllMaterials();
		return new ResponseEntity(mats,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findMaterialById(@PathVariable long id){
		Material mat = materialRepository.findOne(id);
		return new ResponseEntity(mat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMaterialById(@PathVariable long id){
		if(!materialRepository.exists(id)) {
			return new ResponseEntity("Material id : "+id+" does not exists",HttpStatus.NO_CONTENT);
		}
		Material mat = materialRepository.findOne(id);
		materialRepository.delete(id);
		return new ResponseEntity(mat,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createMaterial(@Valid @RequestBody Material mat,UriComponentsBuilder ucBuilder){
		if(materialRepository.exists(mat.getId())) {
			return new ResponseEntity("Material id : "+mat.getId()+" already exists",HttpStatus.FORBIDDEN);
		}
		MaterialType type = new MaterialType(mat.getMaterialTypeId());
		mat.setMaterialType(type);
		materialRepository.save(mat);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/materials/{id}").buildAndExpand(mat.getId()).toUri());
		return new ResponseEntity(headers,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateMaterial(@Valid @RequestBody Material toBeMat,UriComponentsBuilder ucBuilder){
		Material asIsMat = materialRepository.findOne(toBeMat.getId());
		if(asIsMat==null) {
			return new ResponseEntity("Material id : "+toBeMat.getId()+" does not exists",HttpStatus.NOT_FOUND);
		}
		else if(asIsMat.equals(toBeMat)) {
			return new ResponseEntity("Material id : "+toBeMat.getId()+" does not exists",HttpStatus.NOT_MODIFIED);
		}
		MaterialType type = new MaterialType(toBeMat.getMaterialTypeId());
		toBeMat.setMaterialType(type);
		materialRepository.save(toBeMat);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/materials/{id}").buildAndExpand(toBeMat.getId()).toUri());
		return new ResponseEntity(headers,HttpStatus.OK);
	}
	
}
