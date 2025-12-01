package clover.pd.rest;

import clover.pd.entity.Product;
import clover.pd.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService ps;

    public ProductController(ProductService ps){
        this.ps = ps;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return ps.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return ps.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return ps.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return ps.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        ps.deleteProduct(id);
        return "Successful";
    }
}
