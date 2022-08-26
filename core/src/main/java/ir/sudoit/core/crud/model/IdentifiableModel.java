package ir.sudoit.core.crud.model;

import java.io.Serializable;

public interface IdentifiableModel<T extends Serializable> extends Serializable
{

    T getId();
}