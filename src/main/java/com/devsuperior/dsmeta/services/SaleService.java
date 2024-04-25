package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerNameDTO;
import com.devsuperior.dsmeta.entities.Seller;
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
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SellerNameDTO> findByRequestParams(String minDate, String maxDate, String name, Pageable pageable){
		DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataMax = maxDate.isBlank() ? LocalDate.now() : LocalDate.parse(maxDate, formatoEntrada);
		LocalDate dataMin = minDate.isBlank() ? dataMax.minusYears(1) : LocalDate.parse(minDate, formatoEntrada);
		Page<Seller> result = repository.searchByName(name, pageable);
		return result.map(x -> new SellerNameDTO(x));
	}
}
