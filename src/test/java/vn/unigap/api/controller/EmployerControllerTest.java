package vn.unigap.api.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hibernate.mapping.Array;
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

import java.util.ArrayList;
import java.util.Arrays;
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
}