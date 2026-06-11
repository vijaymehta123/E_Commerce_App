package com.myFirstProject.myFirstProject.DTO;

public class ProductRequestDto {
    private String name;
    private String desc;
    private Double price;
    private String category;
    private String img;
    private String slug;
    private Integer discount;

    public ProductRequestDto() {}

    public ProductRequestDto(String name, String desc, Double price,
                             String category, String img,
                             String slug, Integer discount) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.img = img;
        this.slug = slug;
        this.discount = discount;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
