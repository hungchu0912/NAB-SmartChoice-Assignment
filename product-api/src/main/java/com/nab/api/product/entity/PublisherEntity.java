package com.nab.api.product.entity;

import com.nab.api.product.entity.constant.PublisherName;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "publisher")
@Data
public class PublisherEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private PublisherName name;
    @Column(name = "description")
    private String description;
    @Column(name = "homepage_url")
    private String homepageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherEntity that = (PublisherEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(homepageUrl, that.homepageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, homepageUrl);
    }
}
