package org.workat.workat_project.wish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.workat.workat_project.wish.entity.WishListDTO;
import org.workat.workat_project.wish.service.WishService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/wish")
@AllArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping("/list")
    public ResponseEntity<List<WishListDTO>> userWishList(Principal principal, String category) {
        return ResponseEntity.ok(wishService.userWishList("user1@example.com",category));
    }
}
