package org.workat.workat_project.wish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.wish.entity.WishListDTO;
import org.workat.workat_project.wish.entity.WishVO;
import org.workat.workat_project.wish.service.WishService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/wish")
@AllArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping("/list")
    public ResponseEntity<List<WishListDTO>> userWishList(Authentication principal, String category) {
        return ResponseEntity.ok(wishService.userWishList("user1@example.com", category));
    }

    @PostMapping("/update")
    public ResponseEntity<WishVO> addWish(Authentication principal, @RequestBody WishVO wishVO) {
        return ResponseEntity.ok(wishService.updateUserWish("user1@example.com", wishVO.getPlace_id(), wishVO.getLiked()));
    }
}
