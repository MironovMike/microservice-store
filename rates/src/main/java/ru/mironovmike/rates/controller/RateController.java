package ru.mironovmike.rates.controller;

import ru.mironovmike.rates.entity.Rate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mironovmike.rates.service.RateService;

@RestController
@RequestMapping(value = "v1/rate")
@RequiredArgsConstructor
@Slf4j
public class RateController {
    @Autowired
    private final RateService rateService;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<Rate> getRate(@PathVariable("code") String code) {
        return ResponseEntity.ok(rateService.getRate(code));
    }
}
