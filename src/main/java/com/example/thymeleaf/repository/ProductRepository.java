package com.example.thymeleaf.repository;

import com.example.thymeleaf.domain.Comment;
import com.example.thymeleaf.domain.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductRepository {

    private final Product[] prod = new Product[31];

    public ProductRepository() {
        prod[0] = new Product(0, "Fresh Sweet Basil", true, new BigDecimal("4.99"));
        prod[1] = new Product(1, "Italian Tomato", false, new BigDecimal("1.25"),
                new Comment(1, "I'm so sad this product is no longer available!"),
                new Comment(2, "When do you expect to have it back?"));
        prod[2] = new Product(2, "Yellow Bell Pepper", true, new BigDecimal("2.50"));
        prod[3] = new Product(3, "Old Cheddar", true, new BigDecimal("18.75"));
        prod[4] = new Product(4, "Extra Virgin Coconut Oil", true, new BigDecimal("6.34"));
        prod[5] = new Product(5, "Organic Tomato Ketchup", true, new BigDecimal("1.99"));
        prod[6] = new Product(6, "Whole Grain Oatmeal Cereal", true, new BigDecimal("3.07"));
        prod[7] = new Product(7, "Traditional Tomato & Basil Sauce", true, new BigDecimal("2.58"));
        prod[8] = new Product(8, "Quinoa Flour", true, new BigDecimal("3.02"),
                new Comment(11, "Made bread with this and it was great!"),
                new Comment(12, "Note: this comes actually mixed with wheat flour"));
        prod[9] = new Product(9, "Grapefruit Juice", true, new BigDecimal("2.58"));
        prod[10] = new Product(10, "100% Pure Maple Syrup", true, new BigDecimal("5.98"));
        prod[11] = new Product(11, "Marinara Pasta Sauce", false, new BigDecimal("2.08"));
        prod[12] = new Product(12, "Vanilla Puff Cereal", false, new BigDecimal("1.75"),
                new Comment(3, "Very tasty! I'd definitely buy it again!"),
                new Comment(3, "Very tasty! I'd definitely buy it again!"),
                new Comment(4, "My kids love it!"),
                new Comment(5, "Good, my basic breakfast cereal. Though maybe a bit in the sweet side..."),
                new Comment(6, "Not that I find it bad, but I think the vanilla flavouring is too intrusive"),
                new Comment(7, "I agree with the excessive flavouring, but still one of my favourites!"),
                new Comment(8, "Cheaper than at the local store!"),
                new Comment(9, "I'm sorry to disagree, but IMO these are far too sweet"),
                new Comment(10, "Good. Pricey though."));
        prod[13] = new Product(13, "Extra Virgin Oil", false, new BigDecimal("5.01"),
                new Comment(13, "Awesome Spanish oil. Buy it now."),
                new Comment(14, "Would definitely buy it again. Best one I've tasted"),
                new Comment(15, "A bit acid for my taste, but still a very nice one."),
                new Comment(16, "Definitely not the average olive oil. Really good."));
        prod[14] = new Product(14, "Roasted Garlic Pasta Sauce", true, new BigDecimal("2.40"));
        prod[15] = new Product(15, "Canned Minestrone Soup", true, new BigDecimal("2.19"));
        prod[16] = new Product(16, "Almond Milk 1L", true, new BigDecimal("3.24"),
                new Comment(17, "Great value!"));
        prod[17] = new Product(17, "Organic Chicken & Wild Rice Soup", true, new BigDecimal("3.17"));
        prod[18] = new Product(18, "Purple Carrot, Blackberry, Quinoa & Greek Yogurt", true, new BigDecimal("8.88"));
        prod[19] = new Product(19, "Pumpkin, Carrot and Apple Juice", false, new BigDecimal("3.90"));
        prod[20] = new Product(20, "Organic Canola Oil", true, new BigDecimal("10.13"));
        prod[21] = new Product(21, "Potato Corn Tortilla Chips", true, new BigDecimal("2.44"));
        prod[22] = new Product(22, "Canned Corn Chowder Soup", true, new BigDecimal("2.30"));
        prod[23] = new Product(23, "Organic Lemonade Juice", true, new BigDecimal("2.48"),
                new Comment(18, "My favourite :)"));
        prod[24] = new Product(24, "Spicy Basil Dressing", true, new BigDecimal("4.72"));
        prod[25] = new Product(25, "Sweet Agave Nectar", true, new BigDecimal("6.46"));
        prod[26] = new Product(26, "Dark Roasted Peanut Butter", false, new BigDecimal("3.48"));
        prod[27] = new Product(27, "Unsweetened Lemon Green Tea", true, new BigDecimal("18.34"));
        prod[28] = new Product(28, "Whole Grain Flakes Cereal", true, new BigDecimal("3.52"));
        prod[29] = new Product(29, "Berry Chewy Granola Bars", true, new BigDecimal("4.00"),
                new Comment(19, "Too hard! I would not buy again"),
                new Comment(20, "Taste is OK, but I agree with previous comment that bars are too hard to eat"),
                new Comment(21, "Would definitely NOT buy again. Simply unedible!"));
    }

    public List<Product> findAll() {
        return List.of(prod);
    }

    public Product findById(int id) {
        return prod[id];
    }
}
