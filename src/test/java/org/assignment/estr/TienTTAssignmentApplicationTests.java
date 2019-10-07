package org.assignment.estr;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assignment.estr.constant.Status;
import org.assignment.estr.entity.WorkEntity;
import org.assignment.estr.form.WorkForm;
import org.assignment.estr.repository.WorkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TienTTAssignmentApplicationTests {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WorkRepository mockRepository;

	@Before
	public void init() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-01 00:00:00");
		WorkEntity workEntity = new WorkEntity(1L, "Tony", date, date, Status.PLANNING.toString());
		when(mockRepository.findById(1L)).thenReturn(Optional.of(workEntity));
	}

	@Test
	public void findWorkOK() throws Exception {

		mockMvc.perform(get("/api/works/1"))
				/* .andDo(print()) */
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Tony")))
				.andExpect(jsonPath("$.startingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.endingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.status", is("PLANNING")));

		verify(mockRepository, times(1)).findById(1L);

	}

	@Test
	public void findAllWorkOK() throws Exception {

		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-01 00:00:00");
		Pageable paging = PageRequest.of(0, 10, org.springframework.data.domain.Sort.by("id"));
		List<WorkEntity> workEntities = Arrays.asList(
				new WorkEntity(1L, "Tony1", date, date, Status.PLANNING.toString()),
				new WorkEntity(2L, "Adam", date, date, Status.DOING.toString()));
		Page<WorkEntity> pages = new PageImpl<WorkEntity>(workEntities.subList(0, 2), paging, workEntities.size());


		when(mockRepository.findAll(paging)).thenReturn(pages);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("pageNo", "0");
		params.add("pageSize", "10");
		params.add("sortBy", "id");
		mockMvc.perform(
				get("/api/works")
				)
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content.[0].id", is(1)))
				.andExpect(jsonPath("$.content.[0].name", is("Tony1")))
				.andExpect(jsonPath("$.content.[0].startingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.content.[0].endingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.content.[0].status", is("PLANNING")))
				.andExpect(jsonPath("$.content.[1].id", is(2)))
				.andExpect(jsonPath("$.content.[1].name", is("Adam")))
				.andExpect(jsonPath("$.content.[1].startingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.content.[1].endingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.content.[1].status", is("DOING")));

		verify(mockRepository, times(1)).findAll(paging);
	}

	@Test
	public void findWorkIdNotFound404() throws Exception {
		mockMvc.perform(get("/api/works/111")).andExpect(status().isNotFound());
	}

	@Test
	public void saveWorkOK() throws Exception {
		
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-01 00:00:00");

		WorkEntity newWorkEntity = new WorkEntity(3L, "Tony2", date, date, Status.PLANNING.toString());
		when(mockRepository.save(any(WorkEntity.class))).thenReturn(newWorkEntity);
		
		WorkForm form = new WorkForm(3L, "Tony2", "2019-10-01 00:00:00", "2019-10-01 00:00:00", Status.PLANNING.toString());
		
		mockMvc.perform(post("/api/works").content(objectMapper.writeValueAsString(form)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON))
				/* .andDo(print()) */
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(3)))
				.andExpect(jsonPath("$.name", is("Tony2")))
				.andExpect(jsonPath("$.startingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.endingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.status", is("PLANNING")));

		verify(mockRepository, times(1)).save(any(WorkEntity.class));

	}
	
	@Test
    public void updateWorkOK() throws Exception {

		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-01 00:00:00");
		WorkEntity updateWork = new WorkEntity(1L, "Tien", date, date, Status.COMPLETE.toString());
        when(mockRepository.save(any(WorkEntity.class))).thenReturn(updateWork);

        WorkForm form = new WorkForm(1L, "Tien", "2019-10-01 00:00:00", "2019-10-01 00:00:00", Status.COMPLETE.toString());
        mockMvc.perform(put("/api/works/1")
                .content(objectMapper.writeValueAsString(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Tien")))
				.andExpect(jsonPath("$.startingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.endingDate", is("2019-09-30T17:00:00.000+0000")))
				.andExpect(jsonPath("$.status", is("COMPLETE")));
    }
	
    @Test
    public void deleteWorkOK() throws Exception {

        doNothing().when(mockRepository).deleteById(1L);

        mockMvc.perform(delete("/api/works/1"))
                /*.andDo(print())*/
                .andExpect(status().isOk());

        verify(mockRepository, times(1)).deleteById(1L);
    }

}
