package com.example.seChallenge.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seChallenge.domain.Friend;
import com.example.seChallenge.domain.Invoice;
import com.example.seChallenge.domain.InvoiceDetail;
import com.example.seChallenge.domain.Transaction;
import com.example.seChallenge.friend.FriendsService;
import com.example.seChallenge.invoice.dto.OrderResponseDTO;
import com.example.seChallenge.payment.PaymentServiceFactory;
import com.example.seChallenge.repository.InvoiceRepository;
import com.example.seChallenge.repository.TransactionRepository;
import com.example.seChallenge.repository.InvoiceDetailRepository;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private PaymentServiceFactory paymentServiceFactory;

    public OrderResponseDTO[] calculateFoodPayment(Invoice invoice) throws Exception {
        this.invoiceRepository.save(invoice);

        for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
            InvoiceDetail newDetail = new InvoiceDetail(detail.getFriendId(), invoice, detail.getProductName(), detail.getPrice());
            detail = invoiceDetailRepository.save(newDetail);
        }
        
        BigDecimal totalOrderValue = invoice.getTotalOrderValueWithoutDiscountAndDelivery();

        Map<String, OrderResponseDTO> orderMap = new HashMap();
        for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
            Friend friend = this.friendsService.getFriend(detail.getFriendId());
            OrderResponseDTO response = orderMap.getOrDefault(friend.getFullName(), new OrderResponseDTO());
            
            response.setClientName(friend.getFullName());
            response.setTotalOrderValue(invoice.getTotalOrderByFriendId(detail.getFriendId()));
            response.setFriend(friend);

            response.setPercentageValue(response.getTotalOrderValue()
                    .multiply(new BigDecimal(100))
                    .divide(totalOrderValue, 2, RoundingMode.HALF_UP));

            response.setTotalPaymentValue(response.getTotalOrderValue());

            orderMap.put(friend.getFullName(), response);
        }

        OrderResponseDTO[] responses = orderMap.values().toArray(new OrderResponseDTO[0]);

        for (OrderResponseDTO response: responses) {
            response.calculateDiscount(new BigDecimal(invoice.getDiscount()));
            response.calculateFee(new BigDecimal(invoice.getDeliveryFee()), invoice.getPercentageFee());

            Transaction transaction = new Transaction(invoice, response.getFriend(), response.getTotalPaymentValue());
            String link = paymentServiceFactory.getService("PIC_PAY").processPayment(transaction);
            transaction.setPaymentLink(link);
            response.setPaymentLink(link);
            this.transactionRepository.save(transaction);
        }

        Arrays.sort(responses, Comparator.comparing(OrderResponseDTO::getClientName));

        return responses;
   }

   public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
   }
}
