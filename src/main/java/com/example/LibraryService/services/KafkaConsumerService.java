package com.example.LibraryService.services;

import com.example.LibraryService.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final BookService bookService;
    private final String containerFactory = "kafkaListenerContainerFactory";

    @KafkaListener(
            topics = "${spring.kafka.topics.reservation}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = containerFactory)
    public void handleReservationEvent( BookDTO event) {
        BookDTO bookDTO = bookService.getBookById(event.getId());
        bookDTO.setCopies(bookDTO.getCopies()-1);
        bookService.updateBook(event.getId(), bookDTO);
        log.info("Event received from book-reservation-topic");
    }

    @KafkaListener(
            topics = "${spring.kafka.topics.returned}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = containerFactory)
    public void handleReturnedEvent( BookDTO event) {
        BookDTO bookDTO = bookService.getBookById(event.getId());
        bookDTO.setCopies(bookDTO.getCopies()+1);
        bookService.updateBook(event.getId(), bookDTO);
        log.info("Event received from book-returned-topic");
    }
}
