package com.example.product;

import com.example._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    // 상품명 중복체크
    @GetMapping("/api/name-same-check")
    public @ResponseBody ApiUtil<?> productSameCheck(String name) {
        productService.isProductNameExists(name);
        if (name == null) { // 등록 해도 된다.
            return new ApiUtil<>(true);
        } else { // 등록 하면 안된다.
            return new ApiUtil<>(false);
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
