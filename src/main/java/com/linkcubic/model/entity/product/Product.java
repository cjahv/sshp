package com.linkcubic.model.entity.product;

import com.linkcubic.core.factory.ShortLinkFactory;
import com.linkcubic.model.enums.Currency;
import com.linkcubic.model.enums.product.Sex;
import com.sshp.model.entity.BaseEntity;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：产品实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月03日 17:48
 */
@Entity
@Table(name = "lc_products")
public class Product extends BaseEntity {
    private String url;//产品路径
    private Long advertiserId;//广告主Id
    private String sku;
    private String productName;//商品名称
    private String image;//图片路径
    private float price;//商品价格
    private Currency currency;//货币种类 默认为0
    private String brand;//商品品牌
    private Brand brandRef;//商品品牌引用
    private int sort;//推荐排序
    private String description;//产品描述
    private String advertiserName;//广告主名称
    private Date addTime;//添加时间
    private Date editTime;//修改时间
    private String shortUrl;
    private boolean favorite;//当前用户是否收藏
    private Long collectAmount;//收藏量
    private String cdnImage;//cdn加速图片
    private Integer rate;
    private Category category;
    private Sex gender;

    public Product(Long id) {
        this();
        super.setId(id);
    }

    public Product() {
        this.addTime = new Date();
        this.editTime = new Date();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "advitiser")
    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        if (StringUtils.isNotEmpty(cdnImage)) return cdnImage;
        return image;
    }

    @Transient
    public String getFormerImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Transient
    public String getBrand() {
        if (brandRef != null && StringUtils.isNotEmpty(brandRef.getName())) {
            return brandRef.getName();
        }
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand getBrandRef() {
        return brandRef;
    }

    public void setBrandRef(Brand brandRef) {
        this.brandRef = brandRef;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "advitisername")
    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    @Column(updatable = false)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Column(insertable = false)
    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Transient
    public String getShortUrl() {
        if (shortUrl == null) {
            ShortLinkFactory.generate(this);
        }
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Transient
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    //    @Formula("(select count(1) from lcv2_user_favorite_products p where p.product=id)")
    @Column(name = "collect_amount")
    public Long getCollectAmount() {
        return collectAmount;
    }

    public void setCollectAmount(Long collectAmount) {
        this.collectAmount = collectAmount;
    }

    @Column(name = "local_image")
    public String getCdnImage() {
        return cdnImage;
    }

    public void setCdnImage(String cdnImage) {
        this.cdnImage = cdnImage;
    }

    @Column(name = "discount_rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @ManyToOne
    @JoinColumn(name = "category_L1")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }
}
