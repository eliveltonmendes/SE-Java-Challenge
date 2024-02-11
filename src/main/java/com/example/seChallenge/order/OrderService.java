package com.example.seChallenge.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.seChallenge.order.dto.OrderDTO;
import com.example.seChallenge.order.dto.OrderDetailDTO;
import com.example.seChallenge.order.dto.OrderResponseDTO;

@Service
public class OrderService {
    public OrderResponseDTO[] calculateOrderDivision(OrderDTO order) {
        BigDecimal totalOrderValue = order.getTotalOrderValueWithoutDiscountAndDelivery();

        Map<String, OrderResponseDTO> orderMap = new HashMap<>();
        for (OrderDetailDTO detail : order.getDetails()) {
            OrderResponseDTO response = orderMap.getOrDefault(detail.getClientName(), new OrderResponseDTO());
            response.setClientName(detail.getClientName());
            BigDecimal userTotalOrderValue = detail.getProductValue();
            if (response.getTotalOrderValue() != null) {
                userTotalOrderValue = userTotalOrderValue.add(response.getTotalOrderValue())
                    .setScale(2, RoundingMode.HALF_UP);
            }

            response.setTotalOrderValue(userTotalOrderValue);

            BigDecimal percentage = response.getTotalOrderValue()
                .multiply(new BigDecimal(100))
                .divide(totalOrderValue, 2, RoundingMode.HALF_UP);
            response.setPercentageValue(percentage);
            orderMap.put(detail.getClientName(), response);
        }

        // Convert map values to array
        OrderResponseDTO[] responses = orderMap.values().toArray(new OrderResponseDTO[0]);

        //Share discount and delivery according to user percentage
        for (OrderResponseDTO response : responses) {
            BigDecimal discountValue = new BigDecimal(0);
            BigDecimal deliveryValue = new BigDecimal(0);;

            if (order.getDeliveryValue().intValue() > 0) {
                deliveryValue = response.getPercentageValue().divide(new BigDecimal(100)).multiply(order.getDeliveryValue()).setScale(2, RoundingMode.HALF_UP);;
            }

            if (order.getDiscountValue().intValue() > 0) {
                discountValue = response.getPercentageValue().divide(new BigDecimal(100)).multiply(order.getDiscountValue()).setScale(2, RoundingMode.HALF_UP);;
            }

            BigDecimal totalPaymentValue = response.getTotalOrderValue().subtract(discountValue).add(deliveryValue).setScale(2, RoundingMode.HALF_UP);
            response.setTotalPaymentValue(totalPaymentValue);
        }

        //Sort response by clientName
        Arrays.sort(responses, Comparator.comparing(OrderResponseDTO::getClientName));

        return responses;
    }
}
