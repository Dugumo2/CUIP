package com.epoch.controller;


import com.epoch.model.vo.Result;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签目录 前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/label-directory")
public class LabelDirectoryController {
    /**
     * 获取标签列表
     * @return 结果
     */
    @GetMapping("getLabels")
    public Result getLabels() {
        return null;
    }

}
