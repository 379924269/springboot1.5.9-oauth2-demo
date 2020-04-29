package moe.cnkirito.security.oauth2.code.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "IdVo")
@XmlAccessorType(XmlAccessType.NONE)
@ApiModel(value = "IdVo", description = "返回添加数据的id")
public class IdVo implements Serializable {
    private static final long serialVersionUID = -5466182189778460506L;

    @XmlAttribute
    @ApiModelProperty(value = "表id", dataType = "Integer")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
