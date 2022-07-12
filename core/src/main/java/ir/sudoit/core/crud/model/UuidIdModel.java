package ir.sudoit.core.crud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public abstract class UuidIdModel extends BaseModel<UUID>{

    private UUID id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean isDeleted = Boolean.FALSE;

    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UuidIdModel that = (UuidIdModel) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(createdDate, that.createdDate)) return false;
        if (!Objects.equals(updatedDate, that.updatedDate)) return false;
        if (!Objects.equals(isDeleted, that.isDeleted)) return false;
        return Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
