package com.example.bloggingapp.about;


import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController {

    @GetMapping("")
    String Hello()
    {
        return "This is new project";
    }
    @GetMapping("/private")
    String pvtAbt(@AuthenticationPrincipal UserResponseDto userResponseDto)
    {
        var username=userResponseDto.getUsername();
        return "This is new private project "+username;
    }

}
