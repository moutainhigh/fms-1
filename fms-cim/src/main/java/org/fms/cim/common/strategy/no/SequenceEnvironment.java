package org.fms.cim.common.strategy.no;

import java.util.List;

/**
 * 自定义号 环境角色
 */
public class SequenceEnvironment {
    private SequenceStrategy sequenceStrategy;

    public SequenceEnvironment(SequenceStrategy sequenceStrategy) {
        this.sequenceStrategy = sequenceStrategy;
    }

    public String generateSequenceNo(String condition){
        return  this.sequenceStrategy.generateSequenceNo(condition);
    }

    public String generateSequenceNo(String condition, int size){
        return  this.sequenceStrategy.generateSequenceNo(condition,size);
    }
}
