package com.example.wxgzh.picture.controller;

import com.example.wxgzh.common.dto.JSONResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/o")
public class OtpController {

    @PostMapping("/o")
    @ResponseBody
    public JSONResponse avatar(@RequestParam(value="file")MultipartFile file)throws Exception{
        return JSONResponse.ok();
    }
}
