package com.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.entity.Category;
import com.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.spec.ECGenParameterSpec;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // 新增分类
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category ： {}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }
    // 分页查询
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize){
        // 分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        // 条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        // 调用service
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);

    }
    // 根据id删除分类
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("需要删除的id为", id);
        // 以下是调用了自定义的remove方法
        categoryService.remove(id);
        return R.success("删除成功");
    }
    // 根据id修改分类信息
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("需要修改的信息为{}",category);
        // 根据自定义方法自动填充公共信息
        categoryService.updateById(category);
        return R.success("修改成功");
    }
    // 根据条件查询分类数据
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        // 条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        // 添加条件
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        // 添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
