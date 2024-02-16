package com.tatva.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tatva.dto.request.ChatGptRequest;
import com.tatva.dto.request.Message;
import com.tatva.dto.response.ChatGptResponse;
import com.tatva.service.QuestionService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/text")
public class CustomBotController {
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate template;
	
	private QuestionService questionService;
	
	@Autowired
	public CustomBotController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	@PostMapping("/tellme")
	public ResponseEntity<String> chat(@RequestBody String text) {
//		String text="hello Mohit How are you I am fine and you currently I am working in that was soft and my experience is almost 2 7 years and I am talking about my javascrills so so yeah I have some basic idea of course Java Spring boot spring MBC and talking about the database site so yeah no is about the my SQL my SQL jdbc Oracle post grace and talking about my like talking about the or I am so yeah I have a basic knowledge of Hibernate and GPA and also the knowledge of the GST of late this is from my side bye";
//		ChatGptRequest request= new ChatGptRequest();
//		request.setModel(model);
//		List<Message> messages=new ArrayList<Message>();
//		Message message=new Message();
//		message.setRole("user");
//		message.setContent("Correct this to standard English:" + text);
//		//message.setContent(question);
//		messages.add(message);
//		//messages.add("Correct this to standard English:")
//		request.setMessages(messages);
//		
//		//store question in database
//	//	questionService.storeQuestion(question);
//		
//		ChatGptResponse chatGptResponse=template.postForObject(apiUrl, request, ChatGptResponse.class);
////		
//		return ResponseEntity.ok(chatGptResponse.getChoices().get(0).getMessage().getContent());
		System.out.println("Text is" + " " + text);
		return ResponseEntity.ok(text);
		}

}
