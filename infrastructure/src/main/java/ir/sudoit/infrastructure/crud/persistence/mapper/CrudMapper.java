package ir.sudoit.infrastructure.crud.persistence.mapper;

import ir.sudoit.core.crud.model.IdentifiableModel;
import ir.sudoit.core.crud.port.dto.CrudRequest;
import ir.sudoit.core.crud.port.dto.CrudResponse;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.io.Serializable;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@MapperConfig(
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValueCheckStrategy = ALWAYS,
        nullValuePropertyMappingStrategy = IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CrudMapper<T extends IdentifiableModel<ID>, ID extends Serializable, Q extends CrudRequest, S extends CrudResponse> {


    T toCreate(Q request);


    T toUpdate(Q request, @MappingTarget T target);


    S toResponse(T entity);
}