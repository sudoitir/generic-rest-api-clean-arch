package ir.sudoit.core.crud.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public abstract class BaseModel <T extends Serializable> implements IdentifiableModel<T>{
}
