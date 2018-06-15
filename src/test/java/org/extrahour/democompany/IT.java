package org.extrahour.democompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoCompanyApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class IT {

  @Autowired
  protected WebApplicationContext context;

  protected MockMvc mockMvc;

  protected ObjectMapper objectMapper = new ObjectMapper();

  @Before
  public void prepareMockMvc() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .build();
  }

}
