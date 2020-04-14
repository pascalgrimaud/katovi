package io.github.pascalgrimaud.katovi.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageMapperTest {
    private MessageMapper messageMapper;

    @BeforeEach
    public void setUp() {
        messageMapper = new MessageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(messageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(messageMapper.fromId(null)).isNull();
    }
}
