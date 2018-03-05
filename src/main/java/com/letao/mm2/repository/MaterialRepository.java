package com.letao.mm2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.letao.mm2.model.Material;

public interface MaterialRepository extends PagingAndSortingRepository<Material,Long> {

	@Query("select m from Material m")
	List<Material> listAllMaterials();
}
