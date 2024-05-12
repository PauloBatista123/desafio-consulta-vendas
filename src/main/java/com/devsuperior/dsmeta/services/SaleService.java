package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummary;
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

    public List<SaleSummary> getSummary(String minDate, String maxDate) {
		LocalDate dateIni = minDate != null ? LocalDate.parse(minDate) : LocalDate.now(ZoneId.systemDefault()).minusYears(1L);
		LocalDate dateMax = maxDate != null ? LocalDate.parse(maxDate) : LocalDate.now(ZoneId.systemDefault());

		List<SaleSummary> summary = repository.findSummary(dateIni, dateMax);

		return summary;
	}

	public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate dateIni = minDate != null ? LocalDate.parse(minDate) : LocalDate.now(ZoneId.systemDefault()).minusYears(1L);
		LocalDate dateMax = maxDate != null ? LocalDate.parse(maxDate) : LocalDate.now(ZoneId.systemDefault());
		name = name != null ? name.toLowerCase() : "";

		Page<SaleMinDTO> report = repository.findReport(dateIni, dateMax, name, pageable);

		return report;
	}
}
