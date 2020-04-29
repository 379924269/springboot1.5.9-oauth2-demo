package moe.cnkirito.security.oauth2.code.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "pageVo")
@XmlAccessorType(XmlAccessType.NONE)
@ApiModel(value = "pavaVo", description = "分页信息")
public class PageVo implements Serializable {

    private static final long serialVersionUID = -5466182189778460506L;

    @XmlAttribute
    @ApiModelProperty(value = "升序降序字段,如：casc 升序 desc降序，暂时没写代码，根据实际情况商量处理。默认id desc", dataType = "String")
    private String order;
    // 排序字段，可选，默认id
    @XmlAttribute
    @ApiModelProperty(value = "排序字段", dataType = "String")
    private String sort;
    // 查询偏移量，可选，默认0
    @XmlAttribute
    @ApiModelProperty(value = "偏移量", dataType = "Integer")
    private Integer offset;
    // 查询长度，可选，默认20
    @XmlAttribute
    @ApiModelProperty(value = "页大小", dataType = "Integer")
    private Integer limit;

    public PageVo() {
    }

    public String getOrder() {
        if (StringUtils.isEmpty(order)) {
            this.order = "desc";
        }
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        if (StringUtils.isEmpty(sort)) {
            this.sort = "id";
        }
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        if (isEmptyNumber(limit)) {
            this.limit = 20;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        if (isEmptyNumber(offset)) {
            this.offset = 0;
        }

        if (offset < 20) {
            return 1;
        } else {
            //这个地方是为了适应mybaitsplus 的page
            return (offset - 1) / 20 + 1;
        }
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public static boolean isEmptyNumber(Integer number) {
        if (number == null || String.valueOf(number) == "") {
            return true;
        } else {
            return false;
        }
    }
}
