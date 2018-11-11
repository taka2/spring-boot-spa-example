package springbootspaexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {
	@GetMapping("/list")
	public String index() {
		return "['aaa', 'bbb']";
	}
}
