package pipeandfilterarchitecture.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
interface CurrencyDefinitionJpaRepository extends org.springframework.data.repository.Repository<CurrencyDefinitionEntity, UUID> {
    @Transactional(propagation = Propagation.MANDATORY)
    Stream<CurrencyDefinitionEntity> findAll();
}
