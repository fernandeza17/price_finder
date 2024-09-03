package com.fernandeza.price_finder.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.domain.service.PriceService;
import com.fernandeza.price_finder.infrastructure.adapters.input.rest.data.response.FindPriceResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceRestTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Test
    public void testFindPriceCase1410() throws Exception {
        Price expectedPrice = getPrice1();
        Mockito.when(
                priceService.findPriceByDateBrandAndProduct(LocalDateTime.parse("2020-06-14-10.00.00", formatter), 1L, 35455L)
        ).thenReturn(expectedPrice);

        MvcResult result = mockMvc.perform(get("/v1/prices/2020-06-14-10.00.00/1/35455"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FindPriceResponse actualResponse = objectMapper.readValue(json, FindPriceResponse.class);

        assertTrue(isSamePriceResponse(expectedPrice, actualResponse));
    }

    @Test
    public void testFindPriceCase1416() throws Exception {
        Price expectedPrice = getPrice2();
        Mockito.when(
                priceService.findPriceByDateBrandAndProduct(LocalDateTime.parse("2020-06-14-16.00.00", formatter), 1L, 35455L)
        ).thenReturn(expectedPrice);

        MvcResult result = mockMvc.perform(get("/v1/prices/2020-06-14-16.00.00/1/35455"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FindPriceResponse actualResponse = objectMapper.readValue(json, FindPriceResponse.class);

        assertTrue(isSamePriceResponse(expectedPrice, actualResponse));
    }

    @Test
    public void testFindPriceCase1421() throws Exception {
        Price expectedPrice = getPrice1();
        Mockito.when(
                priceService.findPriceByDateBrandAndProduct(LocalDateTime.parse("2020-06-14-21.00.00", formatter), 1L, 35455L)
        ).thenReturn(expectedPrice);

        MvcResult result = mockMvc.perform(get("/v1/prices/2020-06-14-21.00.00/1/35455"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FindPriceResponse actualResponse = objectMapper.readValue(json, FindPriceResponse.class);

        assertTrue(isSamePriceResponse(expectedPrice, actualResponse));
    }

    @Test
    public void testFindPriceCase1510() throws Exception {
        Price expectedPrice = getPrice3();
        Mockito.when(
                priceService.findPriceByDateBrandAndProduct(LocalDateTime.parse("2020-06-15-10.00.00", formatter), 1L, 35455L)
        ).thenReturn(expectedPrice);

        MvcResult result = mockMvc.perform(get("/v1/prices/2020-06-15-10.00.00/1/35455"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FindPriceResponse actualResponse = objectMapper.readValue(json, FindPriceResponse.class);

        assertTrue(isSamePriceResponse(expectedPrice, actualResponse));
    }

    @Test
    public void testFindPriceCase1621() throws Exception {
        Price expectedPrice = getPrice4();
        Mockito.when(
                priceService.findPriceByDateBrandAndProduct(LocalDateTime.parse("2020-06-16-21.00.00", formatter), 1L, 35455L)
        ).thenReturn(expectedPrice);

        MvcResult result = mockMvc.perform(get("/v1/prices/2020-06-16-21.00.00/1/35455"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FindPriceResponse actualResponse = objectMapper.readValue(json, FindPriceResponse.class);

        assertTrue(isSamePriceResponse(expectedPrice, actualResponse));
    }

    private boolean isSamePriceResponse(Price expected, FindPriceResponse actualResponse) {
        return actualResponse.getProductId().equals(expected.getProductId())
                && actualResponse.getBrandId().equals(expected.getBrandId())
                && actualResponse.getPriceList().equals(expected.getPriceList())
                && actualResponse.getStartDate().equals(expected.getStartDate())
                && actualResponse.getEndDate().equals(expected.getEndDate())
                && actualResponse.getValue().equals(expected.getValue())
                && actualResponse.getCurrency().equals(expected.getCurrency());
    }

    private Price getPrice1() {
        return new Price(1L, LocalDateTime.parse("2020-06-14-00.00.00", formatter),
                LocalDateTime.parse("2020-12-31-23.59.59", formatter),1L,35455L,0,35.50,"EUR");
    }

    private Price getPrice2() {
        return new Price(1L, LocalDateTime.parse("2020-06-14-15.00.00", formatter),
                LocalDateTime.parse("2020-06-14-18.30.00", formatter),2L,35455L,1,24.45,"EUR");
    }

    private Price getPrice3() {
        return new Price(1L, LocalDateTime.parse("2020-06-15-00.00.00", formatter),
                LocalDateTime.parse("2020-06-15-11.00.00", formatter),3L,35455L,1,30.50,"EUR");
    }

    private Price getPrice4() {
        return new Price(1L, LocalDateTime.parse("2020-06-15-16.00.00", formatter),
                LocalDateTime.parse("2020-12-31-23.59.59", formatter),4L,35455L,1,38.95,"EUR");
    }
}
