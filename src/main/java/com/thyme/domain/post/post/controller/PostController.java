package com.thyme.domain.post.post.controller;

import javax.naming.Binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@Validated  // @NotBlank, @Length를 사용하기 위해 @Validated 애너테이션을 클래스에 적용해야 한다
			// Validation은 `spring-boot-starter-validation` dependency가 추가되어야 사용할 수 있다
@RequestMapping("/posts")
@Controller
public class PostController {

	// 서버로부터 정보를 요청하기 위한 메서드로 GET을 사용한다
	@GetMapping("/write")
	@ResponseBody
	public String showWrite() {
		return """
			<form method="post">
				<input type="text" name="title" placeholder="제목" /> <br>
				<textarea name="content"></textarea>
				<input type="submit" value="등록" />
			</form>
			""";
		// form: 사용자가 입력한 데이터를 서버로 보내준다
		// form action: 응답을 어디로 보낼지 지정할 수 있다 '/' 하나로 앞주소가 대체된다
		// form method="post"로 데이터 제출의 HTTP 메서드를 POST로 설정할 수 있다

		// action에 따로 값을 정하지 않으면 입력 데이터를 GET 파라미터로 만들어 요청을 보낸다
		// 글쓰기 과정: 글쓰기 작성 Form -> 입력 완료 -> 처리 : Form을 보여주는 것과 데이터를 처리하는 부분은 서로 필요하므로 같은 엔드포인트가 이상적이다
		// GET /posts/write: 폼 보여주기, GET /posts/write: 처리 이렇게는 구분되지 않아 사용할 수 없다
		// GET /posts/write: 폼 보여주기, POST /posts/write: 동일한 주소라도 HTTP 메서드가 다르면 가능하다
	}

	@AllArgsConstructor
	@Getter
	public static class WriteForm { // doWrite()의 파라미터들을 클래스로 분리했다
		// 필드명과 GET 파라미터명은 여전히 같아야 한다
		@NotBlank(message = "제목을 입력해주세요.") @Length(min = 5, message = "제목은 5글자 이상입니다.")
		private String title; // null이면 안되고, 5글자 이상이어야 한다

		@NotBlank(message = "제목은 5글자 이상입니다.") @Length(min = 10, message = "내용은 10글자 이상입니다.")
		private String content; // null이면 안되고, 10글자 이상이어야 한다
	}

	// 서버로 데이터를 제출하기 위한 메서드로 POST를 사용한다
	@PostMapping("/write")
	@ResponseBody
	public String doWrite(@Valid WriteForm form, BindingResult bindingResult) {
		// @ModelAttribute: 매개변수로 객체를 받겠다는 뜻
		// Model Attribute에 Validation을 적용하려면 파라미터에 @Valid를 적용해야 한다
		// @ModelAttribute는 생략할 수 있다

		// 파라미터에 BindingResult를 추가하니, Validation이 무시된다
		// BindingResult는 validation의 결과를 수집하고, 프로그램은 그대로 실행된다

		return """
			<h1>게시물 조회</h1>
			<div>%s</div>
			<div>%s</div>
			""".formatted(form.getTitle(), form.getContent());
	}
}
