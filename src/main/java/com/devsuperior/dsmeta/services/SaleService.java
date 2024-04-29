package com.devsuperior.dsmeta.services;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.services.exception.DateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	LocalDate maxDate;
	LocalDate minDate;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findByRequestParams(String minDate, String maxDate, String name, Pageable pageable){
		dateValidation(minDate, maxDate);
		return repository.searchByName(this.minDate, this.maxDate, name, pageable);
	}

    public Page<SaleReportDTO> report(String minDate, String maxDate, Pageable pageable) {
		dateValidation(minDate, maxDate);
		return  repository.searchSumarry(this.minDate, this.maxDate, pageable);
    }

	private void dateValidation(String minDate, String maxDate){
		this.maxDate = maxDate.isBlank() ? LocalDate.now() : LocalDate.parse(maxDate);
		this.minDate = minDate.isBlank() ? this.maxDate.minusYears(1) : LocalDate.parse(minDate);
		if(this.minDate.isAfter(this.maxDate)){
			throw new DateException("A data de início não pode ser superior a de fim");
		}
	}
}
