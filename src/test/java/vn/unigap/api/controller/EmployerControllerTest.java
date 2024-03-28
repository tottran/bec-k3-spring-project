package vn.unigap.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.entity.jpa.Employer;
import vn.unigap.api.service.EmployerService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployerController.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;


    @Test
    public void testGetEmployerById() throws Exception {
        // Setup
        Long id = 1L;
        Employer employer = new Employer();
        employer.setId(id);
        employer.setName("John Doe");
        employer.setEmail("john@gmail.com");
        employer.setProvince(1);

        given(employerService.get(id)).willReturn(EmployerDtoOut.from(employer));

        // Execute and assert
        mockMvc.perform(get("http://localhost:4001/employers/" + id))
                .andDo(print()) // In ra log
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.object.id").exists())
                 .andExpect(jsonPath("$.object.name", is(employer.getName())));
    }

     @Test
     public void testGetEmployers() throws Exception {
         // Mock data
         EmployerDtoOut employer1 = new EmployerDtoOut(1L, "email1@example.com", "Employer 1", 1, "Province 1", "Description 1");
         EmployerDtoOut employer2 = new EmployerDtoOut(2L, "email2@example.com", "Employer 2", 2, "Province 2", "Description 2");
         List<EmployerDtoOut> employers = Arrays.asList(employer1, employer2);
         PageDtoOut<EmployerDtoOut> pageDtoOut = new PageDtoOut<>(1, 10, 2L, 1L, employers);

         // Mock service response
         given(employerService.list(any(PageDtoIn.class))).willReturn(pageDtoOut);

         // Perform GET request to controller endpoint
         mockMvc.perform(get("http://localhost:4001/employers").contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.object.page").value(1))
                 .andExpect(jsonPath("$.object.pageSize").value(10))
                 .andExpect(jsonPath("$.object.totalPages").value(1))
                 .andExpect(jsonPath("$.object.totalElements").value(2))
                 .andExpect(jsonPath("$.object.data.length()").value(2))
                 .andExpect(jsonPath("$.object.data[0].id").value(1))
                 .andExpect(jsonPath("$.object.data[0].name").value("Employer 1"))
                 .andExpect(jsonPath("$.object.data[0].email").value("email1@example.com"))
                 .andExpect(jsonPath("$.object.data[1].id").value(2))
                 .andExpect(jsonPath("$.object.data[1].name").value("Employer 2"))
                 .andExpect(jsonPath("$.object.data[1].email").value("email2@example.com"))
                ;
     }

    @Test
    public void testAddEmployer() throws Exception {
         // Mock data
         Employer employer1 = new Employer(1L, "email1@example.com", "Employer 1", 1, "descriptions", new Date(), new Date());

         // Mock service response
         given(employerService.add(any(Employer.class))).willReturn(employer1);

         // Perform POST request to controller endpoint
         mockMvc.perform(
                    post("http://localhost:4001/employers/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(employer1))
                 ).andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.object.id").value(1))
                 .andExpect(jsonPath("$.object.name").value("Employer 1"))
                 .andExpect(jsonPath("$.object.email").value("email1@example.com"));
     }

    @Test
    public void testUpdateEmployer() throws Exception {
        // Mock data
        Long id = 1L;
        Employer updatedEmployer = new Employer();
        updatedEmployer.setId(id);
        updatedEmployer.setName("Updated Employer");

        // Khởi tạo đối tượng response cho service
        Employer employer = new Employer(id, "updatedemail@example.com", "Updated Employer", 1, "Description", new Date(), new Date());
        given(employerService.update(anyLong(), any(Employer.class))).willReturn(employer);

        // Perform PUT request to controller endpoint
        mockMvc.perform(put("http://localhost:4001/employers/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedEmployer))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.object.id").value(id))
                .andExpect(jsonPath("$.object.name").value("Updated Employer"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }


    @Test
    public void testDeleteEmployer() throws Exception {
        // Mock data
        Long id = 1L;

        // Perform PUT request to controller endpoint
        mockMvc.perform(delete("http://localhost:4001/employers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

