package org.fms.cim.webapp.archives.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;

public class BulkRefundEntity extends BaseRowModel {
    @ExcelProperty(value = "结算户号", index = 0)
    private String settlementNo;

    @ExcelProperty(value = "退费金额", index = 1)
    private BigDecimal refundMoney;

    public String getSettlementNo() {
        return settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        this.settlementNo = settlementNo;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}
