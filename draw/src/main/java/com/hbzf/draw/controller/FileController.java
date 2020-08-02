package com.hbzf.draw.controller;

import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("draw/file")
public class FileController {

    @RequestMapping("/expertUpload")
    //@RequiresPermissions("draw:expert:list")
    public R expertUpload(@RequestParam("file") MultipartFile multipartFile){

        return R.ok();
    }
}
