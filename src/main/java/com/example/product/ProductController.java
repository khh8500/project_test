package com.example.product;

import com.example._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    //상품명 실시간 중복체크 (업데이트용)
    @GetMapping("/product/name-check/update")
    public @ResponseBody ResponseEntity<?> nameSameCheckUpdate(String name, Integer id) {
        Product product = productService.findByNameUpdate(name, id);
        if (product == null) {
            return ResponseEntity.ok(new ApiUtil<>(true)); //상품 등록 가능
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false)); //상품 등록 불가
        }
    }

    // 상품명 실시간 중복체크
    @GetMapping("/product/name-check")
    public @ResponseBody ResponseEntity<?> nameSameCheck(String name, HttpServletResponse response) {
        Product product = productService.findByName(name);
        if (product == null) {
            return ResponseEntity.ok(new ApiUtil<>(true)); //상품 등록 가능
        } else {
            //response.setStatus(400);
            return ResponseEntity.ok(new ApiUtil<>(false)); //상품 등록 불가
        }
    }

    @GetMapping("/product")
    public String list(HttpServletRequest request) {
        List<ProductResponse.MainDTO> respDTO = productService.findAll();
        request.setAttribute("productList", respDTO);

        return "/product/list";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.DetailDTO respDTO = productService.findById(id);
        request.setAttribute("product", respDTO);
        return "product/detail";
    }

    @PostMapping("/product/save")
    public String save(ProductRequest.SaveDTO reqDTO) {
        productService.save(reqDTO);
        return "redirect:/";
    }

    @GetMapping("/product/save-form")
    public String save() {
        return "product/save-form";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable Integer id, ProductRequest.UpdateDTO reqDTO) {
        productService.updateById(id, reqDTO);
        return "redirect:/product/" + id;
    }

    @GetMapping("/product/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.DetailDTO respDTO = productService.findById(id);
        request.setAttribute("product", respDTO);
        return "product/update-form";
    }

    @PostMapping("product/{id}/delete")
    public String delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<ProductResponse.MainDTO> respDTO = productService.findAll();
        request.setAttribute("productList", respDTO);

        return "/index";
    }


}
