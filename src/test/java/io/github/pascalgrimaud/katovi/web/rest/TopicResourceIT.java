package io.github.pascalgrimaud.katovi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.pascalgrimaud.katovi.KatoviApp;
import io.github.pascalgrimaud.katovi.domain.Topic;
import io.github.pascalgrimaud.katovi.repository.TopicRepository;
import io.github.pascalgrimaud.katovi.service.TopicService;
import io.github.pascalgrimaud.katovi.service.dto.TopicDTO;
import io.github.pascalgrimaud.katovi.service.mapper.TopicMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TopicResource} REST controller.
 */
@SpringBootTest(classes = KatoviApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TopicResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_KEY_DESERIALIZER = "AAAAAAAAAA";
    private static final String UPDATED_KEY_DESERIALIZER = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE_DESERIALIZER = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_DESERIALIZER = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFSET = "AAAAAAAAAA";
    private static final String UPDATED_OFFSET = "BBBBBBBBBB";

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicService topicService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTopicMockMvc;

    private Topic topic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Topic createEntity(EntityManager em) {
        Topic topic = new Topic()
            .name(DEFAULT_NAME)
            .active(DEFAULT_ACTIVE)
            .keyDeserializer(DEFAULT_KEY_DESERIALIZER)
            .valueDeserializer(DEFAULT_VALUE_DESERIALIZER)
            .groupId(DEFAULT_GROUP_ID)
            .offset(DEFAULT_OFFSET);
        return topic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Topic createUpdatedEntity(EntityManager em) {
        Topic topic = new Topic()
            .name(UPDATED_NAME)
            .active(UPDATED_ACTIVE)
            .keyDeserializer(UPDATED_KEY_DESERIALIZER)
            .valueDeserializer(UPDATED_VALUE_DESERIALIZER)
            .groupId(UPDATED_GROUP_ID)
            .offset(UPDATED_OFFSET);
        return topic;
    }

    @BeforeEach
    public void initTest() {
        topic = createEntity(em);
    }

    @Test
    @Transactional
    public void createTopic() throws Exception {
        int databaseSizeBeforeCreate = topicRepository.findAll().size();

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);
        restTopicMockMvc
            .perform(post("/api/topics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isCreated());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeCreate + 1);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTopic.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testTopic.getKeyDeserializer()).isEqualTo(DEFAULT_KEY_DESERIALIZER);
        assertThat(testTopic.getValueDeserializer()).isEqualTo(DEFAULT_VALUE_DESERIALIZER);
        assertThat(testTopic.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testTopic.getOffset()).isEqualTo(DEFAULT_OFFSET);
    }

    @Test
    @Transactional
    public void createTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = topicRepository.findAll().size();

        // Create the Topic with an existing ID
        topic.setId(1L);
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTopicMockMvc
            .perform(post("/api/topics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTopics() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        // Get all the topicList
        restTopicMockMvc
            .perform(get("/api/topics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(topic.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].keyDeserializer").value(hasItem(DEFAULT_KEY_DESERIALIZER)))
            .andExpect(jsonPath("$.[*].valueDeserializer").value(hasItem(DEFAULT_VALUE_DESERIALIZER)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].offset").value(hasItem(DEFAULT_OFFSET)));
    }

    @Test
    @Transactional
    public void getTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        // Get the topic
        restTopicMockMvc
            .perform(get("/api/topics/{id}", topic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(topic.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.keyDeserializer").value(DEFAULT_KEY_DESERIALIZER))
            .andExpect(jsonPath("$.valueDeserializer").value(DEFAULT_VALUE_DESERIALIZER))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.offset").value(DEFAULT_OFFSET));
    }

    @Test
    @Transactional
    public void getNonExistingTopic() throws Exception {
        // Get the topic
        restTopicMockMvc.perform(get("/api/topics/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeUpdate = topicRepository.findAll().size();

        // Update the topic
        Topic updatedTopic = topicRepository.findById(topic.getId()).get();
        // Disconnect from session so that the updates on updatedTopic are not directly saved in db
        em.detach(updatedTopic);
        updatedTopic
            .name(UPDATED_NAME)
            .active(UPDATED_ACTIVE)
            .keyDeserializer(UPDATED_KEY_DESERIALIZER)
            .valueDeserializer(UPDATED_VALUE_DESERIALIZER)
            .groupId(UPDATED_GROUP_ID)
            .offset(UPDATED_OFFSET);
        TopicDTO topicDTO = topicMapper.toDto(updatedTopic);

        restTopicMockMvc
            .perform(put("/api/topics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isOk());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTopic.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testTopic.getKeyDeserializer()).isEqualTo(UPDATED_KEY_DESERIALIZER);
        assertThat(testTopic.getValueDeserializer()).isEqualTo(UPDATED_VALUE_DESERIALIZER);
        assertThat(testTopic.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testTopic.getOffset()).isEqualTo(UPDATED_OFFSET);
    }

    @Test
    @Transactional
    public void updateNonExistingTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(put("/api/topics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeDelete = topicRepository.findAll().size();

        // Delete the topic
        restTopicMockMvc
            .perform(delete("/api/topics/{id}", topic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
