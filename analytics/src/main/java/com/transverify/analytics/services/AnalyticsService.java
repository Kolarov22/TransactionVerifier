package com.transverify.analytics.services;

import com.transverify.analytics.domain.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnalyticsService {

    public List<UUID> findSuspiciousTransactions(List<TransactionDTO> transactionsToBeVerified){
        List<UUID> chainedTransactions = new ArrayList<>(); // transactions chained in intervals less than 5 minutes
        List<UUID> relatedAccounts; // transactions with related accounts based on lastName

        Map<String, List<TransactionDTO>> groupedBySender = transactionsToBeVerified.stream().
                                                            collect(Collectors.groupingBy(TransactionDTO::getSenderIBAN));

        relatedAccounts = transactionsToBeVerified.stream().filter(t -> {
            String senderLastName = Arrays.asList(t.getSenderName().split(" ")).getLast();
            String receiverLastName = Arrays.asList(t.getReceiverName().split(" ")).getLast();

            return senderLastName.equals(receiverLastName);
        }).map(TransactionDTO::getId).toList();

        for (String senderIBAN : groupedBySender.keySet()){
            var sortedTransactions = groupedBySender.get(senderIBAN).stream().sorted(Comparator.comparing(TransactionDTO::getTimestamp)).toList();

            for (int i = 1; i < sortedTransactions.size(); i++) {
                var prev = sortedTransactions.get(i - 1);
                var curr = sortedTransactions.get(i);

                long timeDifference = Math.abs(Duration.between(prev.getTimestamp(), curr.getTimestamp()).toMinutes());

                if (timeDifference < 3) {
                    chainedTransactions.add(prev.getId());
                    chainedTransactions.add(curr.getId());
                }
            }
        }

        return Stream.concat(chainedTransactions.stream(), relatedAccounts.stream()).distinct().toList();
    }

}
