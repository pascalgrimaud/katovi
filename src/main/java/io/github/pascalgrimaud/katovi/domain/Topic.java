package io.github.pascalgrimaud.katovi.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Topic.
 */
@Entity
@Table(name = "topic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "key_deserializer")
    private String keyDeserializer;

    @Column(name = "value_deserializer")
    private String valueDeserializer;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "jhi_offset")
    private String offset;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Topic name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public Topic active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public Topic keyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
        return this;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public Topic valueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
        return this;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getGroupId() {
        return groupId;
    }

    public Topic groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOffset() {
        return offset;
    }

    public Topic offset(String offset) {
        this.offset = offset;
        return this;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Topic)) {
            return false;
        }
        return id != null && id.equals(((Topic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return (
            "Topic{" +
            "id=" +
            getId() +
            ", name='" +
            getName() +
            "'" +
            ", active='" +
            isActive() +
            "'" +
            ", keyDeserializer='" +
            getKeyDeserializer() +
            "'" +
            ", valueDeserializer='" +
            getValueDeserializer() +
            "'" +
            ", groupId='" +
            getGroupId() +
            "'" +
            ", offset='" +
            getOffset() +
            "'" +
            "}"
        );
    }
}
