package com.mentorandroid.shopbest.models;

import com.android.tonyvu.sc.model.Saleable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "produtos")
public class Product implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    @DatabaseField(generatedId = true)
    private int pId;;
    @DatabaseField()
    private String pName;
    @DatabaseField()
    private String tipo;
    @DatabaseField()
    private String imgUrl;
    @DatabaseField()
    private BigDecimal pPrice;
    @DatabaseField()
    private String pDescription;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        return (this.pId == ((Product) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pName == null ? 0 : pName.hashCode());
        hash = hash * prime + (pPrice == null ? 0 : pPrice.hashCode());
        hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());
        return hash;
    }

    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    public void setName(String name) {
        this.pName = name;
    }

    @Override
    public BigDecimal getPrice() {
        return pPrice;
    }

    @Override
    public String getName() {
        return pName;
    }

    public void setPrice(BigDecimal price) {
        this.pPrice = price;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }


}
