package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SellerRepository;
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
	@Autowired
	private SellerRepository sellerRepository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findByRequestParams(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate dataMax = maxDate.isBlank() ? LocalDate.now() : LocalDate.parse(maxDate);
		LocalDate dataMin = minDate.isBlank() ? dataMax.minusYears(1) : LocalDate.parse(minDate);
		return repository.searchByName(dataMin, dataMax, name, pageable);
	}
}
