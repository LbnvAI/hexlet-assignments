package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);
        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                t -> t.node("title").isEqualTo(task.getTitle()),
                t -> t.node("description").isEqualTo(task.getDescription()));
    }

    @Test
    public void createTest() throws Exception {
        Task task = generateTask();
        var result = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated());
        Task responseTask = taskRepository.findByTitle(task.getTitle()).get();
        assertEquals("createTest ERROR", task.getTitle(), responseTask.getTitle());
    }

    @Test
    public void updateTest() throws Exception {
        Task task = generateTask();
        Task updTask = generateTask();
        taskRepository.save(task);
        mockMvc.perform(put("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updTask)))
                .andExpect(status().isOk());
        Task testTask = taskRepository.findById(task.getId()).get();
        assertEquals("updateTest TITLE ERROR", testTask.getTitle(), updTask.getTitle());
        assertEquals("updateTest DESCRIPTION ERROR", testTask.getDescription(), updTask.getDescription());
    }

    @Test
    public void deleteTest() throws Exception {
        Task task = generateTask();
        Task updTask = generateTask();
        taskRepository.save(task);
        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());
        Task test = taskRepository.findById(task.getId()).orElse(null);
        assertEquals("deleteTest ERROR", test, null);
    }
    // END
}
