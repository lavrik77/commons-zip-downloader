package ru.globaltruck.downloader.fias.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    private Long id;
    private Long objectId;
    private UUID objectGuid;
    private Long changeId;
    private Integer operTypeId;
    private Long prevId;
    private Long nextId;
    private OffsetDateTime updateDate;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Boolean isActual;
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
