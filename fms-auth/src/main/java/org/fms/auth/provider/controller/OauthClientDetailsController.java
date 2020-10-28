package org.fms.auth.provider.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fms.auth.autoconfigure.controller.CrudController;
import org.fms.auth.provider.common.pojo.TableData;
import org.fms.auth.provider.mapper.model.OauthClientDetails;
import org.fms.auth.provider.pojo.ResponseCode;
import org.fms.auth.provider.pojo.ResponseData;
import org.fms.auth.provider.pojo.request.OauthClientDetailsRequest;
import org.fms.auth.provider.service.OauthClientDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;


/**
 * Created by yxs on 2019/1/17.
 */
@RestController
public class OauthClientDetailsController extends CrudController<OauthClientDetails, OauthClientDetailsRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @PostMapping("/client/table")
    @Override
    protected ResponseData<TableData<OauthClientDetails>> queryRecord(@RequestBody OauthClientDetailsRequest query) {
        logger.debug("查询客户端表格");
        Example example = new Example(OauthClientDetails.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(query.getName())) {
            criteria.andLike("name", "%" + query.getName() + "%");
        }
        PageInfo<OauthClientDetails> pageInfo = oauthClientDetailsService.selectByExampleList(example, query.getPageNum(), query.getPageSize());

        return getTableData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), pageInfo);
    }

    @PostMapping("/client/add")
    @Override
    protected ResponseData<OauthClientDetails> addRecord(@RequestBody OauthClientDetails record) {
        logger.debug("添加客户端应用");
        try {
            oauthClientDetailsService.insertSelective(record);
        } catch (Exception e) {
            logger.error("添加客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/client/delete")
    @Override
    protected ResponseData<OauthClientDetails> deleteRecord(@RequestBody List<OauthClientDetails> record) {
        logger.debug("删除客户端应用");
        try {
            oauthClientDetailsService.deleteBatchByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("删除客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/client/update")
    @Override
    protected ResponseData<OauthClientDetails> updateRecord(@RequestBody OauthClientDetails record) {
        logger.debug("更新客户端应用");
        try {
            oauthClientDetailsService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("更新客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/client/validate/{clientId}")
    protected ResponseData<OauthClientDetails> validateClientId(@PathVariable("clientId") String clientId) {
        logger.debug("校验应用id是否存在");
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId(clientId);
        oauthClientDetails = oauthClientDetailsService.selectOne(oauthClientDetails);
        if(oauthClientDetails == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @RequestMapping(value = "/client/all", method = RequestMethod.GET)
    public ResponseData<List<OauthClientDetails>> getAllClient() {
        logger.debug("获取所有客户端应用");
        List<OauthClientDetails> list;
        try {
            list = oauthClientDetailsService.selectAll();
        } catch (Exception e) {
            logger.error("获取所有客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }
}
