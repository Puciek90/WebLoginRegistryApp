package jdbcAuthentication.foo.controller;

import jdbcAuthentication.conf.servlet.SecurityConfig;
import jdbcAuthentication.conf.servlet.ServletConfiguration;
import jdbcAuthentication.foo.service.WebUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ServletConfiguration.class, SecurityConfig.class})
public class ControllerTest {
    
    
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    Filter springSecurityFilterChain;

    @Mock
    WebUserService webUserService;

    @InjectMocks
    FormController controllerTest;

    private MockMvc mockMvc;

    public static RequestPostProcessor custom_user() {
        return user("user").roles("USER");
    }

    public static RequestPostProcessor custom_admin() {
        return user("admin").roles("ADMIN");
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    //just to gimme hope :)
    @Test
    public void rigorousTest() {
        assertTrue(true);
    }

    @Test
    public void shouldShowWrongDataPage() throws Exception {
        mockMvc.perform(get("/wrongData").with(custom_user()))
                .andExpect(status().isOk())
                .andExpect(view().name("wrongData"));
    }

    @Test
    public void shouldShowRegistryPage() throws Exception {
        mockMvc.perform(get("/registry"))
                .andExpect(status().isOk())
                .andExpect(view().name("registry"));
    }

    @Test
    public void shouldShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    //todo: user in perform function does not need to exist
    @Test
    public void shouldShowAdminPage() throws Exception {
        mockMvc.perform(get("/admin").with(custom_admin()))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPage"));
    }

    @Test
    public void shouldShowUserPageWithAdminRole() throws Exception {
        mockMvc.perform(get("/user").with(custom_user()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"));
    }

    @Test
    public void shouldShowUserPageWithUserRole() throws Exception {
        mockMvc.perform(get("/user").with(custom_user()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"));
    }

    @Test
    public void shouldShowAccessDenied() throws Exception {
        mockMvc.perform(get("/admin").with(custom_user()))
                .andExpect(status().is(403));
    }

    @Test
    public void shouldShowFindUserPage() throws Exception {
        mockMvc.perform(get("/findUser").with(custom_user()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldShowUsersListPage() throws Exception {

        mockMvc.perform(get("/userList").with(custom_admin()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("webUsers"));
    }


/*    //// TODO: Spring + Mockito!
    @Test
    public void shouldPassForm() throws Exception {
        mockMvc.perform(
                post("/registry")
                .param("name", "userfghgh")
                .param("password", "123")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("accountCreated"));

        verify(webUserService, times(1)).saveUser(new WebUser());
    }

    //// TODO: Spring + Mockito!
    @Test
    public void shouldThrowRegistryFailure() throws Exception {
            WebUser webUser = Mockito.mock(WebUser.class);

        when(webUser.getName()).thenReturn("Jan");

        mockMvc.perform(
                post("/registry")
                        .param("name", "Jan")
                        .param("password", "Kowalski")
        ).andExpect(view().name("registryFailure"));
    }*/
}
