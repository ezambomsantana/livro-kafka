package com.santana.controller;

import com.santana.dto.ShopReportDTO;
import com.santana.repository.ReportRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop_report")
@RequiredArgsConstructor
public class ShopController {

    private final ReportRepository reportRepository;

    @GetMapping
    public List<ShopReportDTO> getShop() {
        return reportRepository.findAll()
        		.stream()
                .map(shop -> ShopReportDTO.convert(shop))
                .collect(Collectors.toList());
    }

}
