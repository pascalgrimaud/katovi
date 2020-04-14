package io.github.pascalgrimaud.katovi.service.mapper;

import io.github.pascalgrimaud.katovi.domain.*;
import io.github.pascalgrimaud.katovi.service.dto.TopicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Topic} and its DTO {@link TopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {
    default Topic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(id);
        return topic;
    }
}
