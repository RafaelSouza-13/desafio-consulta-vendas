package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;


@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(required = false, defaultValue = "") String minDate,
														 @RequestParam(required = false, defaultValue = "") String maxDate,
														 @RequestParam(required = false, defaultValue = "") String name,
														 Pageable pageable) {
		Page<SaleMinDTO> page = service.findByRequestParams(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(page);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary(@RequestParam(required = false, defaultValue = "") String minDate,
										@RequestParam(required = false, defaultValue = "") String maxDate,
										Pageable pageable) {
		Page<SaleReportDTO> page = service.report(minDate, maxDate, pageable);
		return ResponseEntity.ok(page);
	}
}
