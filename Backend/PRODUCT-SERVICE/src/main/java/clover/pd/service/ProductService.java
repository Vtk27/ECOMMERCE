package clover.pd.service;

import clover.pd.core.doa.factory.ProductRepository;
import clover.pd.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
   private final ProductRepository repo;

   public ProductService(ProductRepository repo){
       this.repo = repo;
   }

   public Product addProduct(Product product){
       return repo.save(product);
   }

   public List<Product> getAllProducts(){
       return repo.findAll();
   }

   public Product getProductById(Long id){
       return repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
   }

   public Product updateProduct(Long id, Product updatedProduct){
       Product existing = getProductById(id);
       existing.setName(updatedProduct.getName());
       existing.setDescription(updatedProduct.getDescription());
       existing.setCost(updatedProduct.getCost());
       existing.setQuantity(updatedProduct.getQuantity());
       existing.setImagesrc(updatedProduct.getImagesrc());
       return repo.save(existing);
   }

   public void deleteProduct(Long id){
       repo.deleteById(id);
   }
}
