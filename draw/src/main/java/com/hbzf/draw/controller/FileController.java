package com.hbzf.draw.controller;

import com.google.common.collect.Lists;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;
import com.hbzf.draw.dao.ExpertDao;
import com.hbzf.draw.entity.dto.ExpertDto;
import com.hbzf.draw.entity.response.UploadFileResponse;
import com.hbzf.draw.enums.UploadResultEnum;
import com.hbzf.draw.service.ExpertService;
import com.hbzf.draw.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("draw/file")
public class FileController {

    @Resource
    private ExpertService expertService;

    @RequestMapping("/expertUpload")
    //@RequiresPermissions("draw:expert:list")
    public R expertUpload(@RequestParam("file") MultipartFile multipartFile) {
        if (Objects.isNull(multipartFile)) {
            return R.error(R.ERROR_CODE, "上传模板为空");
        }
        try {
            List<List<String>> values = ExcelUtil.parseExcelByUrl(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            List<List<String>> result = expertService.batchInsertByLists(values);
            String message = createSuccessMessage(result);
            //生成新excel
            String base64 = createExcel(result);
            return R.ok().put("result", UploadFileResponse.builder().result(message).excelStr(base64).build());
        } catch (IOException e) {
            log.error("expertUpload error", e);
            return R.error(R.ERROR_CODE, R.ERROR_MESSAGE);
        }
    }

    private String createSuccessMessage(List<List<String>> result) {
        int successCount = (int) result.stream().filter(e ->
                UploadResultEnum.SUCCESS.getDesc().equals(e.get(6))).count();
        int failedCount = result.size() - successCount;
        return "添加成功" + successCount + "条，添加失败" + failedCount + "条";
    }

    private String createExcel(List<List<String>> result) {
        List<String> head = Lists.newArrayList("专家名称",
                "专业名称", "手机号", "身份证", "邮箱", "地址", "工作单位", "在职状态", "出生年月", "职称", "是否成功", "失败原因");
        result.add(0, head);
        Workbook workbook = ExcelUtil.createExcelByList(result);
        return ExcelUtil.getBase64ByWorkbook(workbook);
    }


    @RequestMapping("/expertTemplateDown")
    //@RequiresPermissions("draw:expert:list")
    public R expertTemplateDown() {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/filetemplate/专家上传模板.xlsx");
            Workbook workbook = new XSSFWorkbook(inputStream);
            //生成新excel
            String base64 = ExcelUtil.getBase64ByWorkbook(workbook);
            return R.ok().put("result", base64);
        } catch (IOException e) {
            log.error("expertTemplateDown error", e);
            return R.error(R.ERROR_CODE, R.ERROR_MESSAGE);
        }
    }
}
