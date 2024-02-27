package com.tatva.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tatva.constants.ApplicationConstants;
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
		ChatGptRequest request= new ChatGptRequest();
		request.setModel(model);
		List<Message> messages=new ArrayList<Message>();
		Message message=new Message();
		message.setRole("user");
		message.setContent(ApplicationConstants.GRAMMAR_CORRECTION+ text);
		//message.setContent(question);
		messages.add(message);
		//messages.add("Correct this to standard English:")
		request.setMessages(messages);
		
		//store question in database
	//	questionService.storeQuestion(question);
		
		ChatGptResponse chatGptResponse=template.postForObject(apiUrl, request, ChatGptResponse.class);
//		
		return ResponseEntity.ok(chatGptResponse.getChoices().get(0).getMessage().getContent());
		//System.out.println("Text is" + " " + text);
		//return ResponseEntity.ok(text);
		}

}
