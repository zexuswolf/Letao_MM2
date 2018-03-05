package com.letao.mm2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.letao.mm2.model.MaterialType;

@Service
public interface MaterialTypeRepository extends JpaRepository<MaterialType,Long> {

	@Query("select t from MaterialType t")
	List<MaterialType> listAllMaterialTypes();
	
}
