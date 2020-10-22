package org.fms.cim.common.strategy.no;

public interface SequenceStrategy {
    public String generateSequenceNo(String condition);
    public String generateSequenceNo(String condition, int size);
}
