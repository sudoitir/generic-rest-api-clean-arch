package ir.sudoit.core.crud.port.out;

import ir.sudoit.core.crud.model.IdentifiableModel;
import ir.sudoit.core.crud.port.dto.CrudRequest;
import ir.sudoit.core.crud.port.dto.CrudResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T extends IdentifiableModel<ID>, ID extends Serializable, Q extends CrudRequest, S extends CrudResponse> {


    S create(Q source);


    T create(T source);


    S update(ID id, Q source);


    T update(ID id, T source);


    boolean delete(ID id);


    Optional<S> getOne(ID id);


    Optional<T> getOneT(ID id);


    List<S> getAll();


    List<T> getAllT();
}
