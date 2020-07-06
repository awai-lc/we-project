package com.hbzf.draw.entity.response;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2020-07-04
 */
@Data
public class PurWayResponse {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 采购方式（公开招标，竞争性谈判，竞争性磋商，询价采购，其他）
     */
    private String purWay;

}
