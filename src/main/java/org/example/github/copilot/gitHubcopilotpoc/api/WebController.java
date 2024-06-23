package org.example.github.copilot.gitHubcopilotpoc.api;

// create a rest controller
// add request mapping for "/api"
// add get mapping for "/bookInfo" with parameter "bookName" and consumers application/text
// post mapping for "/bookInfo" with consumes application/json and produces application/json
// add a method to write to redis
// add a method to read from redis
// add a delete mapping with value "/deleteBook" and consumes application/json


import org.example.github.copilot.gitHubcopilotpoc.domain.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebController {

	@Autowired
	private RedisTemplate<String, BookInfo> template;
	
	@GetMapping(value = "/bookInfo", produces = "application/json")
	public BookInfo getBookInfo(@RequestParam String bookName) {
		return template.opsForValue().get(bookName);
	}
	
	@PostMapping(value = "/createBook", consumes = "application/json", produces = "application/json")
	public BookInfo postBookInfo(@RequestBody BookInfo bookInfo) {
		template.opsForValue().set(bookInfo.name(), bookInfo);
		return bookInfo;
	}
	
	@DeleteMapping(value = "/deleteBook", consumes = "application/json")
	public void deleteBook(@RequestBody BookInfo bookInfo) {
		template.delete(bookInfo.name());
	}
	
}
