package org.fms.cim.webapp.archives.action;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import org.fms.cim.webapp.archives.domain.MeterWriteSnEntity;
import org.fms.cim.webapp.archives.domain.WriteSectDomain;
import org.fms.cim.webapp.archives.service.IMeterMeterAssetsService;
import org.fms.cim.webapp.archives.service.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.reflect.TypeToken;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("meterMeterAssets")
public class MeterMeterAssetsAction {
    @Autowired
    @Qualifier("meterMeterAssetsServiceImpl")
    private IMeterMeterAssetsService meterMeterAssetsService;

    @Autowired
    @Qualifier("meterServiceImpl")
    private IMeterService meterService;

    //获取计量点与电能表关系
    @ResponseBody
    @PostMapping(params = "method=findByKey")
    public MeterMeterAssetsRelDomain findByKey(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
    	MeterMeterAssetsRelDomain t = GsonUtils.readValue(body, MeterMeterAssetsRelDomain.class);
        return meterMeterAssetsService.findByKey(t);
    }
    
    //获取计量点上下一个表序号
    @ResponseBody
    @PostMapping(params = "method=getNextMeterOrder")
    public Byte getNextMeterOrder(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        Map<String, String> t = GsonUtils.readValue(body, Map.class);
        Long meterId = new Long(t.get("meterId").toString());
        return meterMeterAssetsService.getNextMeterOrder(meterId);
    }

    //获取抄表区段序号
    @ResponseBody
    @PostMapping(params = "method=getAssetsRelByWriteSect")
    public Mono<JsonGrid> getAssetsRelByWriteSect(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        WriteSectDomain t = JSONUtil.readValue(body, WriteSectDomain.class);
        //因为前台不改，为了适用前台修改的sb代码
        if(t.getId()==null&&t.getWriteSectId()!=null) {
        	t.setId(t.getWriteSectId());
        }
        return Mono.just(new JsonGrid(t, meterMeterAssetsService.getMeterOrderByWriteSect(t)));
    }

    //移动抄表区段
    @ResponseBody
    @PostMapping(params = "method=moveWriteSect")
    public Mono<HttpResult> moveWriteSect(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MoveWriteSectEntity moveWriteSectEntity = GsonUtils.readValue(body,
                MoveWriteSectEntity.class);
       // String
        List<MeterDomain> meterDomains=new ArrayList<>();
        List<Long> meterIds=moveWriteSectEntity.getCheckedList();
        //选中记录 移动抄表区段
        if(null==meterIds || meterIds.size()<1){
            //全选
            MeterDomain meterDomain=new MeterDomain();
            meterDomain.setWriteSectionId(moveWriteSectEntity.getOldWriteSectId());
            meterDomain.setPageSize(-1);
            List<MeterDomain> meterDomainList=meterService.findByWhere(meterDomain);
            meterDomainList.stream().forEach(t->{
                meterIds.add(t.getId());
            });
        }

        meterIds.stream().forEach(t->{
            MeterDomain meterDomain=new MeterDomain();
            meterDomain.setId(t);
            meterDomain.setWriteSectionId(moveWriteSectEntity.getWriteSectId());
            meterDomains.add(meterDomain);
        });
        meterService.updateList(meterDomains);
        return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "修改抄表区段成功"));
    }
    //移动抄表区段 接受实体
    class MoveWriteSectEntity {
        private Long writeSectId;
        private Long oldWriteSectId;
        private List<Long> checkedList;

        public Long getWriteSectId() {
            return writeSectId;
        }

        public void setWriteSectId(Long writeSectId) {
            this.writeSectId = writeSectId;
        }

        public Long getOldWriteSectId() {
            return oldWriteSectId;
        }

        public void setOldWriteSectId(Long oldWriteSectId) {
            this.oldWriteSectId = oldWriteSectId;
        }

        public List<Long> getCheckedList() {
            return checkedList;
        }

        public void setCheckedList(List<Long> checkedList) {
            this.checkedList = checkedList;
        }
    }
    //生成抄表序号
    @ResponseBody
    @PostMapping(params = "method=generateWriteOrderAuto")
    public Mono<HttpResult> generateWriteOrderAuto(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        WriteSectDomain writeSectDomain = GsonUtils.readValue(body, WriteSectDomain.class);
        List<MeterWriteSnEntity> list = meterMeterAssetsService.generateWriteOrderAuto(writeSectDomain);
        //存抄表序号
        List<MeterMeterAssetsRelDomain> meterMeterAssetsRelDomains =
                new ArrayList<>();
        list.stream().forEach(t -> {
            MeterMeterAssetsRelDomain meterMeterAssetsRelDomain =
                    new MeterMeterAssetsRelDomain();
            meterMeterAssetsRelDomain.setId(new Long(t.getId()));
            meterMeterAssetsRelDomain.setWriteSn(new Long(t.getRowNum()));
            meterMeterAssetsRelDomains.add(meterMeterAssetsRelDomain);
        });
        int a = meterMeterAssetsService.updateList(meterMeterAssetsRelDomains);
        if (a < 1) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "生成抄表序号错误"));
        } else {
            return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "生成抄表序号成功"));
        }
    }

    //批量调整抄表区段序号

    /**
     * 处理逻辑 传入所选的记录 取出记录的最小的抄表序号
     * 查出该抄表区段的全纪录
     * 筛选出差集 按抄表序号排序
     * 遍历差集 如果与要调整的序号相同
     * 则将选中的记录全插入到差集构成新的去记录
     * 更新全纪录
     */
    @ResponseBody
    @PostMapping(params = "method=adjustWriteSn")
    public Mono<HttpResult> adjustWriteSn(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> map = GsonUtils.readValue(body, Map.class);
        //处理实体
        List<MeterWriteSnEntity> dealMeterWriteSnEntities = new ArrayList<>();
        //更新实体
        List<MeterMeterAssetsRelDomain> meterMeterAssetsRelDomains = new ArrayList<>();
        Type typeOfT = TypeToken.getParameterized(List.class, MeterWriteSnEntity.class).getType();
        //选中的集合
//        List<MeterWriteSnEntity> meterWriteSnEntities =
//                GsonUtils.readValueToList(map.get("moveList").toString(), typeOfT);
        
        List<MeterWriteSnEntity> meterWriteSnEntities =
                GsonUtils.readValueToList(GsonUtils.toJson(map.get("moveList")), MeterWriteSnEntity.class);

        //moveInfo
        //writeSn: 按序号移动,
        //userNo: 按户号移动,
        //firstOrLast: 1最前 2最后
        ParamEntity paramEntity = GsonUtils.readValue(GsonUtils.toJson(map.get("moveInfo")), ParamEntity.class);
        WriteSectDomain writeSectDomain = new WriteSectDomain();
        writeSectDomain.setId(paramEntity.getId());
        writeSectDomain.setPageSize(-1);
        //所有的集合
        List<MeterWriteSnEntity> allmeterMeterAssetsRelDomains = meterMeterAssetsService.getMeterOrderByWriteSect(writeSectDomain);

        //取得差集 按抄表序号排列
        List<MeterWriteSnEntity> diffmeterMeterAssetsRelDomains =
                allmeterMeterAssetsRelDomains.stream().filter(item -> !meterWriteSnEntities.contains(item))
                        .sorted(Comparator.comparing(MeterWriteSnEntity::getWriteSn,Comparator.nullsLast(Long::compareTo))).collect(toList());

        if (null != paramEntity.getWriteSn()) {
            diffmeterMeterAssetsRelDomains.stream().forEach(t -> {
                dealMeterWriteSnEntities.add(t);
                if (t.getWriteSn().equals(paramEntity.getWriteSn())) {
                    dealMeterWriteSnEntities.addAll(meterWriteSnEntities);
                }
            });
        } else if (null != paramEntity.getUserNo()) {
            //插入的地方为最后一个户号的后面 无法使用lambda表达式
            List<String> allUserNos=
                    diffmeterMeterAssetsRelDomains.stream().map(MeterWriteSnEntity::getUserNo).distinct().collect(toList());
            if(!allUserNos.contains(paramEntity.getUserNo())){
                return Mono.just(new HttpResult<>(HttpResult.ERROR,
                        paramEntity.getUserNo()+"不在所选区段"));
            }

            int index=0;
            for (int i = 0; i < diffmeterMeterAssetsRelDomains.size(); i++) {
                //户号相同 的最后位置的下标
                if ((paramEntity.getUserNo()).equals(diffmeterMeterAssetsRelDomains.get(i).getUserNo())) {
                    index=i;
                }
            }
            for (int i = 0; i < diffmeterMeterAssetsRelDomains.size(); i++) {
                dealMeterWriteSnEntities.add(diffmeterMeterAssetsRelDomains.get(i));
                if (i==index) {
                    dealMeterWriteSnEntities.addAll(meterWriteSnEntities);
                }
            }

        } else if (null != paramEntity.getFirstOrLast()) {
            if ("1".equals(paramEntity.getFirstOrLast().toString())) {
                dealMeterWriteSnEntities.addAll(meterWriteSnEntities);
                dealMeterWriteSnEntities.addAll(diffmeterMeterAssetsRelDomains);
            } else {
                dealMeterWriteSnEntities.addAll(diffmeterMeterAssetsRelDomains);
                dealMeterWriteSnEntities.addAll(meterWriteSnEntities);
            }

        } else {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "参数错误"));
        }
        //取得排完顺序的记录
        Integer[] b = {1};
        dealMeterWriteSnEntities.stream().forEach(i -> {
            MeterMeterAssetsRelDomain meterMeterAssetsRelDomain =
                    new MeterMeterAssetsRelDomain();
            meterMeterAssetsRelDomain.setId(i.getId());
            meterMeterAssetsRelDomain.setWriteSn(new Long(b[0].toString()));
            meterMeterAssetsRelDomains.add(meterMeterAssetsRelDomain);
            b[0]++;
        });
        meterMeterAssetsService.updateList(meterMeterAssetsRelDomains);
        return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "调整抄表序号成功"));
    }
    //批量调整抄表区段序号前后台传输对象
    class ParamEntity {
        private Long id;
        private Long writeSn;
        private String userNo;
        private Long firstOrLast;
        private String writeSectNo;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getWriteSn() {
            return writeSn;
        }

        public void setWriteSn(Long writeSn) {
            this.writeSn = writeSn;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public Long getFirstOrLast() {
            return firstOrLast;
        }

        public void setFirstOrLast(Long firstOrLast) {
            this.firstOrLast = firstOrLast;
        }

        public String getWriteSectNo() {
            return writeSectNo;
        }

        public void setWriteSectNo(String writeSectNo) {
            this.writeSectNo = writeSectNo;
        }
    }


    //获取计量点与电能表关系
    @ResponseBody
    @PostMapping(params = "method=findByWhere")
    public List<MeterMeterAssetsRelDomain> findByWhere(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterMeterAssetsRelDomain t = GsonUtils.readValue(body, MeterMeterAssetsRelDomain.class);
        return meterMeterAssetsService.findByWhere(t);
    }

    @ResponseBody
    @PostMapping(params = "method=getMeterAssetsByMeterIds")
    public List<MeterMeterAssetsRelDomain> getMeterAssetsByMeterIds(@RequestBody String meterIds) {
        HashMap hashMap = GsonUtils.readValue(meterIds, HashMap.class);
        List<Long> meterIdList = GsonUtils.readValueToList(GsonUtils.toJson(hashMap.get("meterIds")), Long.class);
        return meterMeterAssetsService.getMeterAssetsByMeterIds(meterIdList);
    }
}
