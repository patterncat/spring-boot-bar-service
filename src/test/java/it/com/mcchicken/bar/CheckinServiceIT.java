package it.com.mcchicken.bar;

import com.mcchicken.bar.untappd.service.CheckinRetrievalService;
import com.mcchicken.bar.service.CheckinServiceController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CheckinServiceIT {
    private MockMvc mockMvc;

    @Mock
    CheckinRetrievalService client;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(new CheckinServiceController(client)).build();

        when(client.fetchCheckins(anyString())).thenReturn(42);
    }

    @Test
    public void returnsCheckinsForUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/checkins/jyg0607")).andReturn();

        assertThat(result.getResponse().getContentAsString(), is("42"));
    }
}
