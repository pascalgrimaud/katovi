package io.github.pascalgrimaud.katovi.service.mapper;

import io.github.pascalgrimaud.katovi.domain.*;
import io.github.pascalgrimaud.katovi.service.dto.MessageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Message} and its DTO {@link MessageDTO}.
 */
@Mapper(componentModel = "spring", uses = { TopicMapper.class })
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {
    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "topic.name", target = "topicName")
    MessageDTO toDto(Message message);

    @Mapping(source = "topicId", target = "topic")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
