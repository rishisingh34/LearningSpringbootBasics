package in.rishirajsingh.learnspringboot;

import org.springframework.web.bind.annotation.*;

// ✅ @RestController
// -----------------
// Combines @Controller + @ResponseBody
// - Marks this class as a RESTful web controller.
// - Every method returns data directly in the HTTP response body (usually JSON).
// - Spring Boot uses Jackson (by default) to automatically convert Java objects → JSON.
@RestController
public class HelloController {

    // ✅ 1. Basic GET endpoint returning JSON
    // ---------------------------------------
    // @GetMapping = shortcut for @RequestMapping(method = RequestMethod.GET)
    // When client calls GET /hello → Spring invokes this method.
    // The returned HelloResponse object is auto-converted to JSON by Jackson.
    //
    // Example response (JSON):
    // {
    //    "message": "Hello World"
    // }
    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse("Hello World");
    }

    // ✅ 2. GET endpoint returning plain text
    // --------------------------------------
    // Return type is String, so Spring sends the string directly as response body.
    // No JSON conversion here.
    //
    // Example response:
    // Hello World
    @GetMapping("/hello-text")
    public String helloText() {
        return "Hello World";
    }

    // ✅ 3. POST endpoint receiving plain text in body
    // ------------------------------------------------
    // - @PostMapping handles HTTP POST requests.
    // - @RequestBody tells Spring to read the raw request body (text or JSON)
    //   and pass it into the method parameter.
    // - Here, we expect a plain text string (not JSON).
    //
    // Example request:
    // POST /hello
    // Body: Rishi
    //
    // Example response (JSON):
    // {
    //   "message": "Hello Rishi, how are you?"
    // }
    @PostMapping("/hello")
    public HelloResponse postHello(@RequestBody String name) {
        return new HelloResponse("Hello " + name + ", how are you?");
    }

    // ✅ 4. POST endpoint returning plain text (no JSON)
    // -------------------------------------------------
    // Similar to the above, but returns a String instead of an object.
    //
    // Example request:
    // POST /hello-text
    // Body: Rishi
    //
    // Example response:
    // Hello Rishi, how are you?
    @PostMapping("/hello-text")
    public String postHelloText(@RequestBody String name) {
        return "Hello " + name + ", how are you?";
    }

    // ✅ 5. Path Variable example
    // ---------------------------
    // @PathVariable extracts a value from the URL itself.
    //
    // Example request:
    // GET /hello/Rishi/show
    //
    // "Rishi" is captured and passed to the method parameter 'name'.
    // Response (JSON):
    // {
    //   "message": "Name: Rishi"
    // }
    @GetMapping("/hello/{name}/show")
    public HelloResponse getNameToShow(@PathVariable String name) {
        return new HelloResponse("Name: " + name);
    }
}

/*
------------------------------------------------------
📘 Notes on @Controller vs @RestController
------------------------------------------------------
@Controller:
  - Used for MVC web applications.
  - Methods return view names (like "home.jsp" or "index.html").
  - You use Model to pass data to the view.

@RestController:
  - Used for REST APIs.
  - Methods return data (objects, strings) → directly written to response body.
  - No views involved.
------------------------------------------------------

📘 About HelloResponse class (used above)
------------------------------------------------------
public class HelloResponse {
    private String message;

    // constructor
    public HelloResponse(String message) {
        this.message = message;
    }

    // getter
    public String getMessage() {
        return message;
    }
}
------------------------------------------------------

📘 Quick Recap
------------------------------------------------------
GET  /hello              → returns JSON
GET  /hello-text         → returns plain text
POST /hello              → accepts plain text body, returns JSON
POST /hello-text         → accepts plain text body, returns plain text
GET  /hello/{name}/show  → reads path variable, returns JSON

🧩 Pro Tip:
If you want to accept or send JSON in POST,
change the parameter type from String → HelloRequest, like:

@PostMapping("/hello")
public HelloResponse postHello(@RequestBody HelloRequest request) {
    return new HelloResponse("Hello " + request.getName());
}

and send JSON like:
{
  "name": "Rishi"
}
------------------------------------------------------
*/