package com.example.pcmspringbot1.util;

import com.example.pcmspringbot1.dto.response.RespGroupMenuDTO;
import com.example.pcmspringbot1.model.GroupMenu;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransformPagination {

    private String sortByColumn;
    private String sort;
    String [] sortArr = new String [2];

    public Map<String,Object> transformPagination(List ls, Page page, String column, String value) {
        sortArr = page.getSort().toString().split(":");
        sortByColumn = sortArr[0];
        Boolean isSorted = sortByColumn.equals("UNSORTED");
        sortByColumn = isSorted?"id":sortByColumn;
        sort = isSorted?"asc":sortArr[1];
        Map<String,Object> map = new HashMap<>();
        map.put("content",ls);
        map.put("total-data",page.getTotalElements());
        map.put("current-page",page.getNumber());
        map.put("total-page",page.getTotalPages());
        map.put("sort",sort.trim().toLowerCase());
        map.put("size-per-page",page.getSize());
        map.put("column-name",column);
        map.put("sort-by",sortByColumn);
        map.put("value",value);
        return map;
    }
}
