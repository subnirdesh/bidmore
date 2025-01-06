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
 * @author nirdeshsubedi
 */
public class InsertionSort {
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
