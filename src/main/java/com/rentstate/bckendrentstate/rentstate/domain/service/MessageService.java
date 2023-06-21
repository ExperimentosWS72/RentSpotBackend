package com.rentstate.bckendrentstate.rentstate.domain.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    Page<Message> getAll(Pageable pageable);

    Message getById(Long messageId);

    Message create(Message message);

    Message update(Long messageId, Message resource);

    ResponseEntity<?> delete(Long messageId);
}
