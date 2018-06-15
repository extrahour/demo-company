package org.extrahour.democompany.company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.extrahour.democompany.IT;
import org.junit.Test;
import org.springframework.http.MediaType;

public class CompanyControllerIT extends IT {

  private Company apple = new Company("Apple Inc",
      "Garden st. 1",
      "Cupertino",
      "USA",
      "info@apple.com",
      "0123456789");

  private Company microsoft = new Company("Microsoft Inc",
      "Washington st. 1",
      "Redmond",
      "USA",
      "info@microsoft.com",
      "987654321");


  @Test
  public void create() throws Exception {
    Company created = create(apple);

    assertThat(created.getId()).isNotZero();
    assertThat(created.getName()).isEqualTo("Apple Inc");
    assertThat(created.getAddress()).isEqualTo("Garden st. 1");
    assertThat(created.getCity()).isEqualTo("Cupertino");
    assertThat(created.getCountry()).isEqualTo("USA");
    assertThat(created.getEmailAddress()).isEqualTo("info@apple.com");
    assertThat(created.getPhoneNumber()).isEqualTo("0123456789");
  }

  @Test
  public void create_invalidData() throws Exception {
    apple.setName("");

    String json = objectMapper.writeValueAsString(apple);
    mockMvc.perform(post("/companies")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void update() throws Exception {
    Company created = create(apple);
    created.setName("New Apple Inc");

    String json = objectMapper.writeValueAsString(created);
    String resultJson = mockMvc.perform(put("/companies/{id}", created.getId())
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Company updated = objectMapper.readValue(resultJson, Company.class);

    assertThat(updated.getId()).isEqualTo(created.getId());
    assertThat(updated.getName()).isEqualTo("New Apple Inc");
  }

  @Test
  public void getOne() throws Exception {
    Company created = create(apple);

    String resultJson = mockMvc.perform(get("/companies/{id}", created.getId()))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Company actual = objectMapper.readValue(resultJson, Company.class);

    assertThat(actual.getId()).isNotZero();
    assertThat(actual.getName()).isEqualTo("Apple Inc");
    assertThat(actual.getAddress()).isEqualTo("Garden st. 1");
    assertThat(actual.getCity()).isEqualTo("Cupertino");
    assertThat(actual.getCountry()).isEqualTo("USA");
    assertThat(actual.getEmailAddress()).isEqualTo("info@apple.com");
    assertThat(actual.getPhoneNumber()).isEqualTo("0123456789");
  }

  @Test
  public void getOne_notFound() throws Exception {
    create(apple);

    mockMvc.perform(get("/companies/{12121312312"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getAll() throws Exception {
    create(apple);
    create(microsoft);

    String resultJson = mockMvc.perform(get("/companies"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    List<Company> companies = objectMapper.readValue(resultJson, new TypeReference<List<Company>>() {
    });

    assertThat(companies).hasSize(2);
    assertThat(companies.get(0).getName()).isEqualTo("Apple Inc");
    assertThat(companies.get(1).getName()).isEqualTo("Microsoft Inc");
  }

  private Company create(Company company) throws Exception {
    String json = objectMapper.writeValueAsString(company);
    String resultJson = mockMvc.perform(post("/companies")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return objectMapper.readValue(resultJson, Company.class);
  }

}
