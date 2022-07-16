package ir.sudoit.infrastructure.crud.persistence.model;

import ir.sudoit.core.crud.model.IdentifiableModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


@ToString
@EqualsAndHashCode
public abstract class BaseEntity<ID extends Serializable> implements IdentifiableModel<ID> {
}