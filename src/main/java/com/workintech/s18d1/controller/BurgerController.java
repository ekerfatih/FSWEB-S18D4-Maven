package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class BurgerController {

    BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping("/burger")
    public List<Burger> getBurgerList() {
        List<Burger> burger = burgerDao.findAll();
        return burger;
    }

    @GetMapping("/burger/{id}")
    public Burger getBurgerById(@PathVariable int id) {
        if (!Validation.isIdValid(id))
            throw new BurgerException("You can't give id lower than 0", HttpStatus.INTERNAL_SERVER_ERROR);
        return burgerDao.findById(id);
    }

    @PostMapping("/burger")
    public ResponseEntity<Burger> createBurger(@RequestBody Burger burger) {
        if (!Validation.isBurgerValid(burger))
            throw new BurgerException("There is a error on burger data", HttpStatus.NOT_ACCEPTABLE);
        burgerDao.save(burger);
        return ResponseEntity.status(HttpStatus.OK).body(burger);
    }

    @PutMapping("/burger")
    public ResponseEntity<Burger> updateBurger(@RequestBody Burger burger) {
        if (!Validation.isBurgerValid(burger))
            throw new BurgerException("There is a error on burger data", HttpStatus.NOT_ACCEPTABLE);

        burgerDao.update(burger);
        return ResponseEntity.status(HttpStatus.OK).body(burger);
    }

    @DeleteMapping("/burger/{id}")
    public void deleteBurger(@PathVariable int id) {
        if (!Validation.isIdValid(id))
            throw new BurgerException("Given id is not valid", HttpStatus.BAD_REQUEST);
        burgerDao.remove(id);
    }

    @GetMapping("/burger/price/{price}")
    public List<Burger> findByPrice(@PathVariable double price) {
        if (!Validation.isPriceValid(price))
            throw new BurgerException("Price can not be lower than 0", HttpStatus.BAD_REQUEST);
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/burger/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        if (!Validation.isBreadTypeValid(breadType))
            throw new BurgerException("Bread Type is faulty please check.", HttpStatus.BAD_REQUEST);
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/burger/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        if (!Validation.isContentValid(content))
            throw new BurgerException("Content can not be empty or null", HttpStatus.BAD_REQUEST);
        return burgerDao.findByContent(content);
    }
}
