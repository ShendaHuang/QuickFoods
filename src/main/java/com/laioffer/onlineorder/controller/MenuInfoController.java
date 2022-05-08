package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.onlineorder.entity.Restaurant;
import com.laioffer.onlineorder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuInfoController {

    private MenuInfoService menuInfoService;

    @Autowired
    public MenuInfoController(MenuInfoService menuInfoService) {
        this.menuInfoService = menuInfoService;
    }

    // /restaurant/1/menu
    @RequestMapping(value = "restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    // /restaurants
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return menuInfoService.getRestaurants();
    }

}
