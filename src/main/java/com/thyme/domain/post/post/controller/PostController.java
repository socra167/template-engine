package com.thyme.domain.post.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/posts")
@Controller
public class PostController {

	@GetMapping("/write")
	@ResponseBody
	public String showWrite() {
		return """
			<form action="/posts/doWrite">
				<input type="text" name="title" placeholder="제목" />
				<textarea name="content"></textarea>
				<input type="submit" value="등록" />
			</form>
			""";
		// form: 사용자가 입력한 데이터를 서버로 보내준다
		// form action: 응답을 어디로 보낼지 지정할 수 있다 '/' 하나로 앞주소가 대체된다
	}

	@GetMapping("/doWrite")
	@ResponseBody
	public String doWrite(String title, String content) { // 파라미터의 이름이 같으면 그대로 사용할 수 있다
		return """
			<h1>게시물 조회</h1>
			<div>%s</div>
			<div>%s</div>
			""".formatted(title, content);
	}
}
