//package protecons.insurance.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import protecons.insurance.security.JwtService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final JwtService jwtService;
//
//    public AuthController(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @PostMapping("/login")
//    public String login() {
//
//        return jwtService.generateToken("amit");
//    }
//}
