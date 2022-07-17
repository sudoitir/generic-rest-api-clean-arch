package ir.sudoit.infrastructure.crud.service;

import ir.sudoit.core.crud.exception.NotFoundException;
import ir.sudoit.core.crud.model.IdentifiableModel;
import ir.sudoit.core.crud.port.dto.CrudRequest;
import ir.sudoit.core.crud.port.dto.CrudResponse;
import ir.sudoit.core.crud.port.out.CrudService;
import ir.sudoit.core.crud.utility.PropertiesConfig;
import ir.sudoit.infrastructure.crud.persistence.mapper.CrudMapper;
import ir.sudoit.infrastructure.crud.persistence.model.EntityEvent;
import ir.sudoit.infrastructure.crud.persistence.repositories.CrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static ir.sudoit.infrastructure.crud.utility.CrudUtils.copyNonNullProperties;

@Transactional
public abstract class AbstractCrudService<T extends IdentifiableModel<ID>, ID extends Serializable, Q extends CrudRequest, S extends CrudResponse> implements CrudService<T,ID,Q,S> {

    protected final CrudRepo<T, ID> repo;
    protected final CrudMapper<T, ID, Q, S> mapper;

    @Autowired
    protected PropertiesConfig propertiesConfig;
    @Autowired
    protected ApplicationEventPublisher publisher;

    protected AbstractCrudService(final CrudRepo<T, ID> repo, final CrudMapper<T, ID, Q, S> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @NonNull
    @Override
    public T create(@NonNull final T source) {
        T entity = repo.create(source);

        EntityEvent<T, ID> event = onCreateEvent(entity);
        if (event != null) publisher.publishEvent(event);

        return entity;
    }

    @NonNull
    @Override
    public S create(@NonNull final Q source) {
        T entity = mapper.toCreate(source);
        onCreate(source, entity);
        repo.create(entity);

        EntityEvent<T, ID> event = onCreateEvent(entity);
        if (event != null) publisher.publishEvent(event);

        return mapper.toResponse(entity);
    }

    @NonNull
    @Override
    public T update(final ID id, final T source) {
        return repo.update(id, source, (s, t) -> copyNonNullProperties(s, t, ignoredProps())).map(entity -> {
            EntityEvent<T, ID> event = onUpdateEvent(entity);
            if (event != null) publisher.publishEvent(event);
            return entity;
        }).orElseThrow(() -> new NotFoundException(propertiesConfig.getResult("not_found_error_code"),
                propertiesConfig.getResult("not_found_error_message")));
    }

    @NonNull
    @Override
    public S update(final ID id, final Q source) {
        return repo.update(id, source, new CallbackMapper<>(mapper::toUpdate, this::onUpdate)).map(entity -> {
            EntityEvent<T, ID> event = onUpdateEvent(entity);
            if (event != null) publisher.publishEvent(event);
            return mapper.toResponse(entity);
        }).orElseThrow(() -> new NotFoundException(propertiesConfig.getResult("not_found_error_code"),
                propertiesConfig.getResult("not_found_error_message")));
    }

    @Override
    public boolean delete(@NonNull final ID id) {
        return repo.del(id).map(deleted -> {
            EntityEvent<T, ID> event = onDeleteEvent(deleted);
            if (event != null) publisher.publishEvent(event);
            return true;
        }).orElse(false);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Optional<T> getOneT(@NonNull final ID id) {
        return Optional.of(repo.getById(id));
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Optional<S> getOne(@NonNull final ID id) {
        return getOneT(id).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<T> getAllT() {
        return repo.getAll();
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public List<S> getAll() {
        return repo.getAll().stream().map(mapper::toResponse).toList();
    }


    protected String[] ignoredProps() {
        return new String[]{"id", "version", "createdAt", "updatedAt"};
    }

    @NonNull
    protected void onCreate(@NonNull Q request, @NonNull T entity) {
    }

    @NonNull
    protected void onUpdate(@NonNull Q request, @NonNull T entity) {
    }

    protected EntityEvent<T, ID> onCreateEvent(@NonNull T entity) {
        return null;
    }

    protected EntityEvent<T, ID> onUpdateEvent(@NonNull T entity) {
        return null;
    }

    protected EntityEvent<T, ID> onDeleteEvent(@NonNull T entity) {
        return null;
    }
}