package com.rentstate.bckendrentstate.rentstate.domain.persistence;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
