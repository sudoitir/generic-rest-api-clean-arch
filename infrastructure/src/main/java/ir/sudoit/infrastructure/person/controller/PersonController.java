package ir.sudoit.infrastructure.person.controller;

import ir.sudoit.core.crud.port.out.CrudService;
import ir.sudoit.core.crud.utility.PropertiesConfig;
import ir.sudoit.core.person.model.PersonModel;
import ir.sudoit.infrastructure.crud.controller.AbstractCrudController;
import ir.sudoit.infrastructure.person.persistence.entity.PersonEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("people")
public class PersonController extends AbstractCrudController<PersonEntity, Long, PersonModel, PersonModel> {

    private final PropertiesConfig propertiesConfig;
    protected PersonController(CrudService<PersonEntity, Long, PersonModel, PersonModel> service, PropertiesConfig propertiesConfig) {
        super(service, propertiesConfig);
        this.propertiesConfig = propertiesConfig;
    }

    @PostMapping
    @Override
    @NonNull
    public ResponseEntity<?> create(@RequestBody @NonNull final PersonModel request) {
        return super.create(request);
    }

    @PatchMapping("/{id}")
    @Override
    @NonNull
    public ResponseEntity<?> update(@RequestBody @NonNull final PersonModel request, @PathVariable("id") @NonNull final Long id) {
        return super.update(request, id);
    }

    @DeleteMapping("/{id}")
    @Override
    @NonNull
    public ResponseEntity<?> delete(@PathVariable("id") @NonNull final Long id) {
        return super.delete(id);
    }

    @GetMapping("/{id}")
    @Override
    @NonNull
    public ResponseEntity<?> getOne(@PathVariable("id") @NonNull final Long id) {
        return super.getOne(id);
    }
}
