package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping("")
    public List<Product> getProductsByPriceBetween(@RequestParam(defaultValue = "0") Integer min,
                                                   @RequestParam(defaultValue = "0") Integer max) {
        Sort sort = Sort.by(Sort.Order.asc("price"));
        if (min != 0 && max != 0) return productRepository.findByPriceBetween(min, max, sort);
        else if (min == 0 && max != 0) return productRepository.findByPriceLessThanEqual(max, sort);
        else if (min != 0 && max == 0) return productRepository.findByPriceGreaterThan(min, sort);
        else return productRepository.findAll();
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
