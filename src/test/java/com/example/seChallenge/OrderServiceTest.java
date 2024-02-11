package com.example.seChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seChallenge.order.OrderService;
import com.example.seChallenge.order.dto.OrderDTO;
import com.example.seChallenge.order.dto.OrderDetailDTO;
import com.example.seChallenge.order.dto.OrderResponseDTO;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testFoodCalculation() {
        // Create sample request body
        OrderDetailDTO[] orderDetail = {
            new OrderDetailDTO("Friend1", "Food1", new BigDecimal("20.0")),
            new OrderDetailDTO("Friend2", "Food2", new BigDecimal("30.0")),
            new OrderDetailDTO("Friend3", "Food3", new BigDecimal("25.0"))
        };

        OrderDTO body = new OrderDTO(orderDetail, new BigDecimal(5), new BigDecimal(2));

        OrderResponseDTO[] response = orderService.calculateOrderDivision(body);

        assertEquals(3, response.length);

        assertEquals("Friend1", response[0].getClientName());
        assertEquals(new BigDecimal("26.67"), response[0].getPercentageValue());
        assertEquals(new BigDecimal("20.0"), response[0].getTotalOrderValue());
        assertEquals(new BigDecimal("19.20"), response[0].getTotalPaymentValue());

        assertEquals("Friend2", response[1].getClientName());
        assertEquals(new BigDecimal("40.00"), response[1].getPercentageValue());
        assertEquals(new BigDecimal("30.0"), response[1].getTotalOrderValue());
        assertEquals(new BigDecimal("28.80"), response[1].getTotalPaymentValue());

        assertEquals("Friend3", response[2].getClientName());
        assertEquals(new BigDecimal("33.33"), response[2].getPercentageValue());
        assertEquals(new BigDecimal("25.0"), response[2].getTotalOrderValue());
        assertEquals(new BigDecimal("24.00"), response[2].getTotalPaymentValue());
    }
}
