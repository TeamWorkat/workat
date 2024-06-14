package org.workat.workat_project.partner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workat.workat_project.partner.entity.PartnerReserveVO;
import org.workat.workat_project.partner.service.PartnerReserveService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerReserveController {
	
	private final PartnerReserveService reserveService;
	
	@GetMapping("/reservelist")
	public ResponseEntity<List<PartnerReserveVO>> getReserveList(){
		return ResponseEntity.ok(reserveService.getReserveList());
	}
	
	@GetMapping("/reserveDetail")
	public ResponseEntity<PartnerReserveVO> getReserveDetail(@RequestParam(name = "res_id") int res_id){
		return ResponseEntity.ok(reserveService.getReserveDetail(res_id));
	}
	
	@GetMapping("/items")
    public Map<String, Object> getItems(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(required = false, name = "search") String search) {
		List<PartnerReserveVO> items = reserveService.getItemList(page, size, search);
        int totalItems = reserveService.getItemCount(search);

        Map<String, Object> response = new HashMap<>();
        response.put("content", items);
        response.put("totalElements", totalItems);
        response.put("totalPages", (int) Math.ceil((double) totalItems / size));
        response.put("currentPage", page);

        return response;
	}
	
//	
//	@PostMapping("/placeUpdate")
//	public ResponseEntity<Integer> updatePlace(@RequestBody PartnerPlaceDTO request){
//		System.out.println(request);
//		return ResponseEntity.ok(placeService.updatePlace(request));
//	}
//	
//	@PostMapping("/placeInsert")
//	public ResponseEntity<Integer> insertPlace(@RequestBody PartnerPlaceDTO request){
//		System.out.println(request);
//		return ResponseEntity.ok(placeService.insertPlace(request));
//	}
//	
}
