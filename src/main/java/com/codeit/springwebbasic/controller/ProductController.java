package com.codeit.springwebbasic.controller;

import com.codeit.springwebbasic.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Controller -> 타임리프 같은 뷰를 직접 핸들링하는 컨트롤러
@RestController // JSON을 리턴하는 컨트롤러
@RequestMapping("/products") // 공통 url 매핑, ProductController는 /products 요청만 받음
public class ProductController {

    // DB가 없으니 가상의 메모리 상품 저장소
    private Map<Long, Product> productMap = new HashMap<>(); // Map<상품번호, 상품객체>

    // 상품의 시리얼넘버를 순차 생성
    private long nextId = 1;

    public ProductController() {
        productMap.put(nextId, new Product(nextId, "에어컨", 1000000)); // 더미데이터 넣기
        nextId++; // 시리얼넘버  1
        productMap.put(nextId, new Product(nextId, "세탁기", 1500000)); // 더미데이터 넣기
        nextId++; // 시리얼넘버  2
        productMap.put(nextId, new Product(nextId, "공기청정기", 300000)); // 더미데이터 넣기
        nextId++; // 시리얼넘버  3
    }

    // 클라이언트에서 JSON 데이터가 아니고 다른 데이터를 보낸다면?
    // 어떻게 요청이 들어왔는지 알아야 맞는 메서드를 준비
    // (GET은 데이터를 운반할 수 없음(JSON), 요청과 함께 데이터를 전달하고 싶어(url+데이터)
    // 1. 쿼리스트링 읽기 -> url?name=value&name=value&....
    // 데이터가 url에 노출되어도 크게 문제가 없는 방식 사용 (조회 -> 검색어, 게시물 조회에서 글 번호 O, ID,PW X)

    // 전통적인 방식
//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public Product getProduct() {}

    // 전통적 방식2
    // http://localhost:8080/products?id=1&price=1000000
//    @GetMapping("/products")
//    public Product getProduct(HttpServletRequest request) {
//        // HttpServletRequest : 요청 관련 정보를 담은 객체
//        String id = request.getParameter("id");
//        String price = request.getParameter("price");
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return productMap.get(Long.parseLong(id));
//    }


    // ?id=???&price=??? 가정 -> localhost:8080/products?id=???&price=???
    // 최근 Spring 방식 , @RequestParam 데이터 타입을 미리 지정
    @GetMapping//공통 매핑이 있어서 괄호 없앰
//    public Product getProduct(Long id, int price) 변수명을 파라미터 변수랑 똑같으면 생략 가능
    public Product getProduct(
            @RequestParam("id") Long id,
            @RequestParam(value = "price", required = false, defaultValue = "5000") int price) { //변수 바로 선언 가능(권장) price는 필수가 아니야.
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return productMap.get(id);
    }

    // url 경로상에다가 특정 값을 묻혀서 전송 (localhost:8080/products/1) -> 1번 상품을 조회
    @GetMapping("/{id}") // url 자체가 바뀜. (1? 2?) {} 사용, /{}/{} 여러개 선언 가능
    public Product getProduct(@PathVariable ("id") Long id){
        System.out.println("id = " + id);
        return productMap.get(id);
    }

}
