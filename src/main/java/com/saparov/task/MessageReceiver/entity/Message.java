package com.saparov.task.MessageReceiver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sender")
	private String sender;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "date")
	@CreationTimestamp
	private Date date;
}
