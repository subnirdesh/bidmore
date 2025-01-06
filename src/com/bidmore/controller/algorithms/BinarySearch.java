/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidmore.controller.algorithms;

import com.bidmore.model.ProductModel;
import java.util.List;

/**
 *
 * @author 23056546 Nirdesh Subedi
 */
public class BinarySearch {
    
    public ProductModel searchByName(String searchValue, List<ProductModel> productList,
        int left, int right) {
    if (right < left) {
        return null;
    }
    
    int mid = (left + right) / 2;
    
    
    
    if (searchValue.toLowerCase().equals(productList.get(mid).getName().toLowerCase())) {
        return productList.get(mid);
    } else if (searchValue.compareToIgnoreCase(productList.get(mid).getName()) < 0) {
     
        return searchByName(searchValue, productList, left, mid - 1);
    } else {
        return searchByName(searchValue, productList, mid + 1, right);
    }
}
    
    
    
}
