package com.saparov.task.MessageReceiver.repository;

import com.saparov.task.MessageReceiver.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	// Метод для поиска сообщений по отправителю
	List<Message> findBySender(String sender);
	
	// Метод для получения последних 10 сообщений
	List<Message> findTop10ByOrderByDateDesc();
}
