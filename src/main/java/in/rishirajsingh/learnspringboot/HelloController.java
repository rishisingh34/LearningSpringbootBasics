package in.rishirajsingh.learnspringboot;

import org.springframework.web.bind.annotation.*;

// Used to create REST web services,
// that return data (JSON or XML) directly to the client.
// Shortcut of @Controller + @ResponseBody
@RestController
public class HelloController {

    // 1. this returns json converted from object obtained ( using jackson )
    @GetMapping("/hello")
    public HelloResponse hello(){
        return new HelloResponse("Hello World");
    }

    // 2. this returns plain text
    @GetMapping("/hello-text")
    public String helloText()  {
        return "Hello World";
    }

    // 3. post
    @PostMapping("/hello")
    public HelloResponse postHello(@RequestBody String name){
        return new HelloResponse("Hello " + name + ", how are you?");
    }

    // 3. post plain text
    @PostMapping("/hello-text")
    public String postHelloText(@RequestBody String name) {
        return "Hello " + name + ", how are you?";
    }

    // 4. Path variable
    @GetMapping("/hello/{name}/show")
    public HelloResponse getNameToShow(@PathVariable String name){
        return new HelloResponse("Name: " + name);
    }
}

//@Controller
//public class MyController {
//
//    @GetMapping("/home")
//    public String homePage(Model model) {
//        model.addAttribute("message", "Welcome to the home page!");
//        return "home"; // returns home.html or home.jsp view
//    }
//}

