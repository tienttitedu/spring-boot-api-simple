package org.assignment.estr.service;

import java.util.List;

import org.assignment.estr.entity.WorkEntity;
import org.assignment.estr.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface WorkService {

	/**
	 * Get all work
	 * 
	 * @return List work in database
	 * @throws Exception 
	 */
	List<WorkEntity> getAll() throws ResourceNotFoundException;
	
	/**
	 * Get all work
	 * 
	 * @return Page work have been paged and sorted in database 
	 * @throws Exception 
	 */
	Page<WorkEntity> getAllPaginationAndSorting(Integer pageNo, Integer pageSize, String sortBy) throws ResourceNotFoundException;

	/**
	 * Save a work
	 * 
	 * @param beer
	 */
	void save(WorkEntity work);

	/**
	 * Get a work
	 * 
	 * @param Long id
	 * @return Work
	 * @throws Exception
	 */
	WorkEntity findById(Long id) throws ResourceNotFoundException;

	/**
	 * Delete a work
	 * 
	 * @param id
	 * @throws Exception 
	 */
	void delete(Long id) throws ResourceNotFoundException;

}
