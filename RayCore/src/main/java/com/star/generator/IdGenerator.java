package com.star.generator;

import com.star.config.Injectors;
import com.star.meta.MachineIdFactory;
import com.star.meta.PartitionFactory;
import com.star.meta.SequenceFactory;
import com.star.meta.TimeStampFactory;

import javax.inject.Inject;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 18:56
 */
public final class IdGenerator {

    private static final String DEFAULT = "default";

    private static IdGenerator idGenerator = new IdGenerator();

    @Inject
    private MachineIdFactory machineIdFactory;

    @Inject
    private TimeStampFactory timeStampFactory;

    @Inject
    private PartitionFactory partitionFactory;

    @Inject
    private SequenceFactory sequenceFactory;

    private IdGenerator(){
        Injectors.inject(this);
    }

    public static IdGenerator getInstance() {
        return idGenerator;
    }

    public long getId(){
        return getId(DEFAULT);
    }

    public long getId(String domain) {

        // 获取机器标识
        long machineId = machineIdFactory.getMachineId();

        // 获取分区
        long index = partitionFactory.getIndex(domain);

        // 获取时间戳, max(current, last)
        long timeStamp = timeStampFactory.getTimeStamp(index);

        // 获取序列
        long sequence = sequenceFactory.getSequence(index, timeStamp);

        // 根据元数据生成ID

        return 0;
    }

}
