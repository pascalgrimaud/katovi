package io.github.pascalgrimaud.katovi.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.pascalgrimaud.katovi.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class MessageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageDTO.class);
        MessageDTO messageDTO1 = new MessageDTO();
        messageDTO1.setId(1L);
        MessageDTO messageDTO2 = new MessageDTO();
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
        messageDTO2.setId(messageDTO1.getId());
        assertThat(messageDTO1).isEqualTo(messageDTO2);
        messageDTO2.setId(2L);
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
        messageDTO1.setId(null);
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
    }
}
