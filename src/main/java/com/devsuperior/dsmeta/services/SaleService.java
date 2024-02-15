package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
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

	public Page<SaleReportDTO> findReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate bigger = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate smaller = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);

		Page<Sale> result = repository.searchReport(smaller, bigger, name, pageable);
		Page<SaleReportDTO> dtos = result.map(x -> new SaleReportDTO(x));
		return dtos;
	}

	public List<SaleSummaryDTO> findSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate bigger = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate smaller = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);

		List<SaleSummaryProjection> result = repository.searchSummary(smaller, bigger);
		List<SaleSummaryDTO> dtos = result.stream().map(x -> new SaleSummaryDTO(x)).collect(Collectors.toList());
		return dtos;
	}
}
