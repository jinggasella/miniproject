package com.eksad.miniproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.miniproject.domain.EnterpriseOrgStructure;
import com.eksad.miniproject.exception.DataNotFoundException;
import com.eksad.miniproject.repository.EnterpriseOrgStructureRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
@Api(tags = "EnterpriseOrgStructure")
public class EnterpriseOrgStructureController {
	
	@Autowired
	private EnterpriseOrgStructureRepository enterpriseOrgStructureRepository;
	
	@GetMapping("ent")
	@ApiOperation(
			value = "API to retrieve all ES's data",
			notes = "Return data with JSON format",
			tags = "Get Data API" 
			)
	public List<EnterpriseOrgStructure> getAllEnterpriseOrgStructures() {
		return enterpriseOrgStructureRepository.findAll();
	}
	
	@GetMapping("ent/{essubcoid}")
	public ResponseEntity<List<EnterpriseOrgStructure>> GetEnterpriseByEsSubcoId(@PathVariable(value = "essubcoid") String esSubcoId){
		
		EnterpriseOrgStructure enterprise = new EnterpriseOrgStructure();
		
		enterprise.setESSubcoID(esSubcoId);
		
		Example<EnterpriseOrgStructure> enterpriseExample = Example.of(enterprise);
		
		List<EnterpriseOrgStructure> enterpriseReturn = enterpriseOrgStructureRepository.findAll(enterpriseExample);
		
		return ResponseEntity.ok().body(enterpriseReturn);
	}

	@PostMapping(value = "/entSave")
	@ApiOperation(
			value = "Add new ES data", 
			notes = "Add new ES data to database",
			tags = {"Data Manipulation API", "" }
			)
	public EnterpriseOrgStructure save(@RequestBody EnterpriseOrgStructure enterpriseOrgStructure) {
		return enterpriseOrgStructureRepository.save(enterpriseOrgStructure);
	}
	
	@PutMapping(value = "ent/{id}")
	@ApiOperation(
			value = "Update new ES data", 
			notes = "Update new ES data to database",
			tags = "Data Manipulation API"
			)
	public ResponseEntity<EnterpriseOrgStructure> UpdateEnterpriseOrgStructure(@Valid @RequestBody EnterpriseOrgStructure enterpriseRequest, @PathVariable(value = "id") Long enterpriseId) throws DataNotFoundException {
		EnterpriseOrgStructure enterpriseOrgStructure = enterpriseOrgStructureRepository.findById(enterpriseId)
				.orElseThrow(() -> new DataNotFoundException("Employee not found for this id ::" + enterpriseId));
		
		enterpriseOrgStructure.setESID(enterpriseRequest.getESID());
		enterpriseOrgStructure.setESName(enterpriseRequest.getESName());
		enterpriseOrgStructure.setESSubcoID(enterpriseRequest.getESSubcoID());
		enterpriseOrgStructure.setCreationSpecification(enterpriseRequest.getCreationSpecification());
		
		final EnterpriseOrgStructure updateEnterpriseOrgStructure = enterpriseOrgStructureRepository.save(enterpriseOrgStructure);
		
	return ResponseEntity.ok().body(updateEnterpriseOrgStructure);
	}
	
	@DeleteMapping("ent/{id}")
	@ApiOperation(
			value = "Delete ES's data",
			notes = "Delete ES data to database",
			tags = "Data Manipulation API" // getAll employees masuk ke kategori Get Data API
			)
	public Map<String, Boolean> deleteEnterpriseOrgStructure(@PathVariable(value = "id") Long enterpriseId)
		throws DataNotFoundException {
		EnterpriseOrgStructure enterpriseOrgStructure = enterpriseOrgStructureRepository.findById(enterpriseId)
				.orElseThrow(() -> new DataNotFoundException("Employee not found for this id ::" + enterpriseId));
		
		enterpriseOrgStructureRepository.delete(enterpriseOrgStructure);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
	return response;
	}
	
	

}