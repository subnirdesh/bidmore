/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidmore.controller.algorithms;

import com.bidmore.model.ProductModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 23056546 Nirdesh Subedi
 */
public class SelectionSort {
    
    public List<ProductModel> sortByProductId(List<ProductModel> unsortedData, boolean isDesc) {
        
        List<ProductModel> dataToSort = new ArrayList(); 
        dataToSort.addAll(unsortedData);
        
        for (int i = 0; i < dataToSort.size() - 1; i++) {
            //min value
            int minIndex = i;
            for (int j = i + 1; j < dataToSort.size(); j++) {
                // compare and assign
                if (isDesc) {
                    if (dataToSort.get(j).getProductId() > dataToSort.get(minIndex).getProductId()) {
                        minIndex = j;
                    }
                } else {
                    if (dataToSort.get(j).getProductId() < dataToSort.get(minIndex).getProductId()) {
                        minIndex = j;
                    }
                }
            }
            //swap
            ProductModel tempProduct = dataToSort.get(minIndex);
            dataToSort.set(minIndex, dataToSort.get(i));
            dataToSort.set(i, tempProduct);
        }
        return dataToSort;
    }
    
    
    public List<ProductModel> sortByName(List<ProductModel> unsortedData, boolean isDesc) {
    List<ProductModel> dataToSort = new ArrayList<>(); 
    dataToSort.addAll(unsortedData);
    
    for (int i = 1; i < dataToSort.size(); i++) {
        ProductModel current = dataToSort.get(i);
        int j = i - 1;
        
        // Move elements that are greater/less than current
        // to one position ahead of their current position
        if (isDesc) {
            // Descending order (larger strings first)
            while (j >= 0 && dataToSort.get(j).getName().compareToIgnoreCase(current.getName()) < 0) {
                dataToSort.set(j + 1, dataToSort.get(j));
                j--;
            }
        } else {
            // Ascending order (smaller strings first)
            while (j >= 0 && dataToSort.get(j).getName().compareToIgnoreCase(current.getName()) > 0) {
                dataToSort.set(j + 1, dataToSort.get(j));
                j--;
            }
        }
        
        // Place current in its correct position
        dataToSort.set(j + 1, current);
    }
    
    return dataToSort;
    }



}
    
    
    
    