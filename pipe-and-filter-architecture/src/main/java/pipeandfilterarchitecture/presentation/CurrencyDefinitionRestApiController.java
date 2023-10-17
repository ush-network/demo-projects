package pipeandfilterarchitecture.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pipeandfilterarchitecture.business.GetCurrencyDefinitionStreamUseCase;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
class CurrencyDefinitionRestApiController {
    private final GetCurrencyDefinitionStreamUseCase getCurrencyDefinitionStreamUseCase;
    private final CurrencyDefinitionDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET, path = "/currency-definition")
    public ResponseEntity<List<CurrencyDefinitionDto>> getCurrencyDefinitions() {
        return ResponseEntity.ok(getCurrencyDefinitionStreamUseCase.execute()
            .map(dtoMapper::map)
            .collect(Collectors.toList()));
    }
}
