package com.saparov.task.MessageReceiver.controller;

import com.saparov.task.MessageReceiver.entity.Message;
import com.saparov.task.MessageReceiver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
	private final MessageService messageService;
	
	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages(){
		List<Message> messages = messageService.getAllMessages();
		return ResponseEntity.ok(messages);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Message getMessageById(@PathVariable Long id){
		return messageService.getMessageById(id);
	}
	
	@GetMapping("/sender/{sender}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Message>> getMessageBySender(@PathVariable String sender){
		List<Message> messages = messageService.getMessageBySender(sender);
		
		if(messages.isEmpty()){
			messages = messageService.getLast10Messages();
		}
		
		return ResponseEntity.ok(messages);
	}
	
	@GetMapping("last10")
	public List<Message> getLast10Messages(){
		return messageService.getLast10Messages();
	}
	
	@PostMapping
	public ResponseEntity<Message> saveMessage(@RequestBody Message message){
		Message createMessage = messageService.saveMessage(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(createMessage);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteMessage(@PathVariable Long id){
		messageService.deleteMessage(id);
		
		return "Message deleted";
	}
}
