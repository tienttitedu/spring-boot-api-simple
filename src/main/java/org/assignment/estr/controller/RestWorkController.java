package org.assignment.estr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.assignment.estr.entity.WorkEntity;
import org.assignment.estr.form.WorkForm;
import org.assignment.estr.service.WorkService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tientt
 *
 */
@RestController
@RequestMapping("/api")
public class RestWorkController {
	
	@Autowired
	DozerBeanMapper beanMapper;

	@Autowired
	WorkService workService;


	@GetMapping("/works")
	public ResponseEntity<Object> getAllWorksPagingAndSorting(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
			throws Exception {
		Page<WorkEntity> listWorks = workService.getAllPaginationAndSorting(pageNo, pageSize, sortBy);
		return new ResponseEntity<>(listWorks, HttpStatus.OK);
	}

	@PostMapping("/works")
	public ResponseEntity<Object> createWork(@Valid @RequestBody WorkForm WorkForm, UriComponentsBuilder builder) {
		WorkEntity workEntity = new WorkEntity();
		// Copy data from form to entity
		beanMapper.map(WorkForm, workEntity);

		// Create Work
		workService.save(workEntity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/works/{id}").buildAndExpand(workEntity.getId()).toUri());
		return new ResponseEntity<>(workEntity, HttpStatus.CREATED);
	}

	@GetMapping("/works/{workId}")
	public ResponseEntity<Object> getWork(@PathVariable Long workId) throws Exception {
		WorkEntity work = workService.findById(workId);
		return new ResponseEntity<>(work, HttpStatus.OK);
	}

	@PutMapping("/works/{workId}")
	public ResponseEntity<Object> updateWork(@PathVariable Long workId, @Valid @RequestBody WorkForm workForm)
			throws Exception {
		WorkEntity updateWork = workService.findById(workId);
		
		// Copy data from form to entity
		beanMapper.map(workForm, updateWork);

		// Update work
		workService.save(updateWork);
		return new ResponseEntity<>(updateWork, HttpStatus.OK);
	}

	@DeleteMapping("/works/{workId}")
	public ResponseEntity<?> deleteWork(@PathVariable Long workId) throws Exception {
		// Destroy work
		workService.delete(workId);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
