package ir.sudoit.infrastructure.crud.controller;

import ir.sudoit.core.crud.model.IdentifiableModel;
import ir.sudoit.core.crud.port.dto.CrudRequest;
import ir.sudoit.core.crud.port.dto.CrudResponse;
import ir.sudoit.core.crud.port.out.CrudService;
import ir.sudoit.core.crud.utility.ApplicationUtilities;
import ir.sudoit.core.crud.utility.PropertiesConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudController<T extends IdentifiableModel<ID>, ID extends Serializable, Q extends CrudRequest, S extends CrudResponse> {

    protected final CrudService<T, ID, Q, S> service;
    protected final PropertiesConfig propertiesConfig;

    protected AbstractCrudController(@NonNull final CrudService<T, ID, Q, S> service, PropertiesConfig propertiesConfig) {
        this.service = service;
        this.propertiesConfig = propertiesConfig;
    }


    @NonNull
    public ResponseEntity<?> create(@NonNull final Q request) {
        service.create(request);
//        return new ResponseEntity<>(ApplicationUtilities.toActionResult(propertiesConfig), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationUtilities.toActionResult(propertiesConfig));
    }


    @NonNull
    public ResponseEntity<?> update(@NonNull final Q request,@NonNull final ID id) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @NonNull
    public ResponseEntity<?> delete(@NonNull final ID id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @NonNull
    public ResponseEntity<?> getOne(@NonNull final ID id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @NonNull
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}