package app.web;

import app.model.Wish;
import app.service.WishService;
import app.web.dto.WishRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static app.TestBuilder.aRandomEditWish;
import static app.TestBuilder.aRandomWish;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishController.class)
public class WishControllerApiTest {

    @MockitoBean
    private WishService wishService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postRequestToCreateNewWishEndPoint_happyPath() throws Exception {

        WishRequest wishRequest = WishRequest.builder()
                .wish("My wish is...")
                .build();

        when(wishService.createWish(wishRequest)).thenReturn(aRandomWish());

        MockHttpServletRequestBuilder request = post("/api/v1/wishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(wishRequest));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("status").isNotEmpty())
                .andExpect(jsonPath("wish").isNotEmpty())
                .andExpect(jsonPath("message").isNotEmpty());

        verify(wishService, times(1)).createWish(wishRequest);
    }

    @Test
    void putRequestToEditWishEndPoint_happyPath() throws Exception {

        WishRequest wishRequest = WishRequest.builder()
                .wish("My new wish is...")
                .build();

        Wish wish = aRandomEditWish();

        when(wishService.editWish(wish.getId() ,wishRequest)).thenReturn(wish);

        MockHttpServletRequestBuilder request = put("/api/v1/wishes/{id}", wish.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(wishRequest));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").isNotEmpty())
                .andExpect(jsonPath("wish").isNotEmpty())
                .andExpect(jsonPath("message").isNotEmpty());

        verify(wishService, times(1)).editWish(wish.getId(), wishRequest);
    }

    @Test
    void deleteRequestToRemoveWishEndPoint_happyPath() throws Exception{

        Wish wish = aRandomWish();

        MockHttpServletRequestBuilder request = delete("/api/v1/wishes/{id}", wish.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("successful").isNotEmpty())
                .andExpect(jsonPath("message").isNotEmpty());

        verify(wishService, times(1)).deleteWish(wish.getId());
    }

    @Test
    void getRequestToGetWishesEndPoint_happyPath() throws Exception{

        List<Wish> wishList = List.of(new Wish(), new Wish());

        when(wishService.getAllWishes()).thenReturn(wishList);

        MockHttpServletRequestBuilder request = get("/api/v1/wishes")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.size()").value(2));

        verify(wishService, times(1)).getAllWishes();
    }
}
