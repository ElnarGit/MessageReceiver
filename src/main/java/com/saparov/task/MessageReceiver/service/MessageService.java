package com.saparov.task.MessageReceiver.service;

import com.saparov.task.MessageReceiver.exception.NotFoundException;
import com.saparov.task.MessageReceiver.entity.Message;
import com.saparov.task.MessageReceiver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
	private final MessageRepository messageRepository;
	
	public List<Message> getAllMessages(){
		return messageRepository.findAll();
	}
	
	public Message getMessageById(Long id){
		return messageRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Message not found with id: " + id));
	}
	
	public List<Message> getMessageBySender(String sender){
		return messageRepository.findBySender(sender);
	}
	
	public List<Message> getLast10Messages(){
		return messageRepository.findTop10ByOrderByDateDesc();
	}
	
	@Transactional
	public Message saveMessage(Message message){
		return messageRepository.save(message);
	}
	
	@Transactional
	public void deleteMessage(Long id){
		Message message = getMessageById(id);
		
		messageRepository.delete(message);
	}
}
