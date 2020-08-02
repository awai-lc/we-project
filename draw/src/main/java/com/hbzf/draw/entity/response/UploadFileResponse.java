package com.hbzf.draw.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传结果
     */
    private String result;
    /**
     * 批量上传结果返回excel(excel转base64)
     */
    private String excelStr;
}
