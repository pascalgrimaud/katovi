package io.github.pascalgrimaud.katovi.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.pascalgrimaud.katovi.domain.Topic} entity.
 */
public class TopicDTO implements Serializable {
    private Long id;

    private String name;

    private Boolean active;

    private String keyDeserializer;

    private String valueDeserializer;

    private String groupId;

    private String offset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TopicDTO topicDTO = (TopicDTO) o;
        if (topicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), topicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return (
            "TopicDTO{" +
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
