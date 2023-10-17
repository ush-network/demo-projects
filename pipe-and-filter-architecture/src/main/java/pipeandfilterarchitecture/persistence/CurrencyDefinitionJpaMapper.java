package pipeandfilterarchitecture.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import pipeandfilterarchitecture.business.CurrencyDefinition;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
interface CurrencyDefinitionJpaMapper {
    CurrencyDefinition map(CurrencyDefinitionEntity entity);
}
