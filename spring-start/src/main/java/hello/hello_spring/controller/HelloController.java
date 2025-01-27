package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//컨트롤러를 통해 외부 요청을 받는다.
@Controller
public class HelloController {

    //리소스에, templete 밑에 있는 폴더 hello를 찾는다
    //뷰는 보여지는 것에 집중해야하고, 컨트롤러와 서비스는 내부로직에 집중해야한다
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    //에러가 나면 로그를 봐야하는데, 파라미터가 없으면 접근이 되지 않는다.
    //required의 기본값은 true이다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-templete";
    }


    //response : http 통신 프로토콜에서 바디 데이터에 직접 넣어주겠다는 의미
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //"hello spring"
    }


    //이 방식은 Json 방식이다. 키와 밸류로 이뤄진 구조이다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();//command + shift + enter 단축키
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
