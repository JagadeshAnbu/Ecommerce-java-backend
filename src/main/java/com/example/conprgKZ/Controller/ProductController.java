    package com.example.conprgKZ.Controller;

    import com.example.conprgKZ.Entity.Product;
    import com.example.conprgKZ.Service.ProductService;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("/api/products")
    public class ProductController {

        private final ProductService productService;

        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @PostMapping
        @CrossOrigin(origins = "http://localhost:3000")
        public ResponseEntity<Product> createProduct(@RequestPart("product") String productJson,
                                                     @RequestPart("productImages") List<MultipartFile> productImages,
                                                     @RequestPart("variationImage") MultipartFile variationImage) {

            // Log the received parameters
            System.out.println("Received product JSON: " + productJson);
            System.out.println("Received variation image: " + variationImage.getOriginalFilename());
            System.out.println("Received product images:");
            for (MultipartFile image : productImages) {
                System.out.println(image.getOriginalFilename());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Product product;
            try {
                product = objectMapper.readValue(productJson, Product.class);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Product createdProduct = productService.createProduct(product, productImages, variationImage);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }

        @GetMapping("/{productId}")
        public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

