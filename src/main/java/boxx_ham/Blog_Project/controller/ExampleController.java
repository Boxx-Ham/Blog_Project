package boxx_ham.Blog_Project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;
import lombok.Setter;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {   // Model : 뷰(HTML) 쪽으로 데이터를 넘겨주는 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        // addAttribute : 모델에 값 저장
        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";   // example.html 이라는 뷰 조회
    }

    @Setter
    @Getter
    class Person {
        private long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
