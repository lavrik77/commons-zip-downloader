package ru.globaltruck.downloader.fias.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "adm_hierarchy", schema = "fias")
public class AdmHierarchyEntity extends BaseEntity {
    private Long parentObjId;
    private String regionCode;
    private String areaCode;
    private String cityCode;
    private String placeCode;
    private String planCode;
    private String streetCode;
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdmHierarchyEntity that = (AdmHierarchyEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
