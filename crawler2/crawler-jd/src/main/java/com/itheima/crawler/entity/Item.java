package com.itheima.crawler.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jd_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="spu")
    private long spu;
    @Column(name="sku")
    private long sku;
    @Column(name="title")
    private String title;
    @Column(name="price")
    private float price;
    @Column(name="pic")
    private String pic;
    @Column(name="url")
    private String url;
    @Column(name="created")
    private Date created;
    @Column(name="updated")
    private Date updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpu() {
        return spu;
    }

    public void setSpu(long spu) {
        this.spu = spu;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", spu=" + spu +
                ", sku=" + sku +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
