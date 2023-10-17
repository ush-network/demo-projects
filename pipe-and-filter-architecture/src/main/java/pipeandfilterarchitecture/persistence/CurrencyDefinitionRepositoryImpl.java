package pipeandfilterarchitecture.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pipeandfilterarchitecture.business.CurrencyDefinition;
import pipeandfilterarchitecture.business.CurrencyDefinitionRepository;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
class CurrencyDefinitionRepositoryImpl implements CurrencyDefinitionRepository {
    private final CurrencyDefinitionJpaRepository jpaRepository;
    private final CurrencyDefinitionJpaMapper jpaMapper;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Stream<CurrencyDefinition> getCurrencyDefinitions() {
        return jpaRepository.findAll().map(jpaMapper::map);
    }
}
