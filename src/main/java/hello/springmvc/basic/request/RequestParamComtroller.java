package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamComtroller {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //@Controller에서 @ResponseBody를 쓰면 문자열로 반환 (view가아니라) - @RestController와 같은효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody //@Controller에서 @ResponseBody를 쓰면 문자열로 반환 (view가아니라) - @RestController와 같은효과
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){//변수명이 파라미터랑 같으면 생략가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){//변수명이 파라미터랑 같으면 생략가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
//            @RequestParam(required = false) int age //이렇게 쓰면 int에는 null을 넣을수 없어서 오류남
            @RequestParam(required = false) Integer age){
        //얘로 확인하면 확실
        // int a = null;
//        Integer b = null;

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue없으면 빈문자일때도 defaultValue로 처리해줌
            @RequestParam(required = true, defaultValue = "guest") String username,

//             @RequestParam(required = false, defaultValue = "-1") int age //defaultValue사용하면 int형도 가능
            @RequestParam(required = false, defaultValue = "-1") Integer age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(
//            @RequestParam String username,
//            @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData);
//        return "ok";
//    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @ModelAttribute("helloData") HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

}
