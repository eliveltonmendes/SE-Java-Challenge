package com.example.seChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seChallenge.domain.Friend;
import com.example.seChallenge.domain.Invoice;
import com.example.seChallenge.domain.InvoiceDetail;
import com.example.seChallenge.friend.FriendsService;
import com.example.seChallenge.invoice.InvoiceService;
import com.example.seChallenge.invoice.dto.OrderResponseDTO;

@SpringBootTest
public class InvoiceServiceTest {
   @Autowired
   private InvoiceService invoiceService;

   @Autowired
   private FriendsService friendsService;

   @Test
   public void testFoodCalculation() {
    Friend friend1 = new Friend("Friend", "1", "first@gmail.com", 71723641);
    friend1 = friendsService.createFriend(friend1);

    Friend friend2 = new Friend("Friend", "2", "second@gmail.com", 71723641);
    friend2 = friendsService.createFriend(friend2);

    Invoice invoice = new Invoice();
    invoice.setDeliveryFee(10.0);
    invoice.setDiscount(5.0);
    invoice.setPercentageFee(10);

    List<InvoiceDetail> invoiceDetails = new ArrayList<>();

    // Add InvoiceDetail objects with the specified values to the list
    invoiceDetails.add(new InvoiceDetail(friend1.getId(), invoice, "Food1", 10.0));
    invoiceDetails.add(new InvoiceDetail(friend2.getId(), invoice, "Food1", 20.0));

    invoice.setInvoiceDetails(invoiceDetails);

       OrderResponseDTO[] response = invoiceService.calculateFoodPayment(invoice);

       assertEquals(2, response.length);

       assertEquals("Friend 1", response[0].getClientName());
       assertEquals(new BigDecimal("33.33"), response[0].getPercentageValue());
       assertEquals(new BigDecimal("10.00"), response[0].getTotalOrderValue());
       assertEquals(new BigDecimal("12.66"), response[0].getTotalPaymentValue());

       assertEquals("Friend 2", response[1].getClientName());
       assertEquals(new BigDecimal("66.67"), response[1].getPercentageValue());
       assertEquals(new BigDecimal("20.00"), response[1].getTotalOrderValue());
       assertEquals(new BigDecimal("25.34"), response[1].getTotalPaymentValue());
   }
}
