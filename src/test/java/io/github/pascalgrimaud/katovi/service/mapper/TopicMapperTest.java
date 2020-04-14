package io.github.pascalgrimaud.katovi.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopicMapperTest {
    private TopicMapper topicMapper;

    @BeforeEach
    public void setUp() {
        topicMapper = new TopicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(topicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(topicMapper.fromId(null)).isNull();
    }
}
