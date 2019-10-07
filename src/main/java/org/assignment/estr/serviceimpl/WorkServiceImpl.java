package org.assignment.estr.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.assignment.estr.entity.WorkEntity;
import org.assignment.estr.exception.ResourceNotFoundException;
import org.assignment.estr.repository.WorkRepository;
import org.assignment.estr.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author tientt 
 * Work service implement Handle business work
 */
@Service
@Transactional
public class WorkServiceImpl implements WorkService {

	@Autowired
	WorkRepository workRepository;

	@Override
	public List<WorkEntity> getAll() throws ResourceNotFoundException {
		List<WorkEntity> listWorks= workRepository.findAll();
		if (listWorks == null || listWorks.isEmpty()) {
			throw new ResourceNotFoundException("List work not found");
		}
		return listWorks;
	}
	
	@Override
	public Page<WorkEntity> getAllPaginationAndSorting(Integer pageNo, Integer pageSize, String sortBy) throws ResourceNotFoundException {
		Pageable paging = PageRequest.of(pageNo, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		Page<WorkEntity> pageResult = workRepository.findAll(paging);
		if (!pageResult.hasContent()) {
			throw new ResourceNotFoundException("List work paging not found");
		}
		return pageResult;
	}

	@Override
	public void save(WorkEntity work) {
		workRepository.save(work);
	}

	@Override
	public WorkEntity findById(Long id) throws ResourceNotFoundException {
		Optional<WorkEntity> work = workRepository.findById(id);
		System.out.println("DEBUG: " + work.get().getId());
		if (work == null || !work.isPresent()) {
			throw new ResourceNotFoundException("Work not found with id " + id);
		}
		return work.get();
	}

	@Override
	public void delete(Long id) throws ResourceNotFoundException {
		Optional<WorkEntity> work = workRepository.findById(id);
		if (work == null || !work.isPresent()) {
			throw new ResourceNotFoundException("Work not found with id " + id);
		}
		workRepository.deleteById(id);

	}

}
