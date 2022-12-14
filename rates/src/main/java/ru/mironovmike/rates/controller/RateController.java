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
import ru.mironovmike.rates.utils.ActionEnum;
import ru.mironovmike.rates.utils.CallResult;

import javax.annotation.security.RolesAllowed;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "v1/rate")
@RequiredArgsConstructor
@Slf4j
public class RateController {
    @Autowired
    private final RateService rateService;

    @RolesAllowed({ "ADMIN", "USER" })
    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<Rate> getRate(@PathVariable("code") String code) throws TimeoutException {
        return ResponseEntity.ok(rateService.getRate(code));
    }

    @RequestMapping(value = "/post/{currency}/{action}", method = RequestMethod.POST)
    public ResponseEntity<CallResult> postCurrencyAction(@PathVariable("currency") String currency, @PathVariable("action") String action) {
        return ResponseEntity.ok(rateService.processCurrencyAction(currency, ActionEnum.valueOf(action.toUpperCase())));
    }
}
