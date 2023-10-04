package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // -> return 하는 문자를 진짜 http body에 박아버림
//@Controller -> view이름 반환
public class LogTestController {
    //편한것으로 쓰면됨
    private final Logger log = LoggerFactory.getLogger(getClass());
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
//        log.trace("trace log ={}, {} ", name, name2); -> 이렇게 쓰면 순서대로 치환이 되는것

        log.trace(" trace log ={}", name);
        log.debug(" debug log ={}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log = {}", name);
        log.error(" error log ={}", name);

        return "ok";
    }
}
