package com.rentstate.bckendrentstate.rentstate.domain.persistence;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRecipient(User recipient);
}
