package com.service.algorithms.schedule.interfaces.criteria.operation.impl;

import com.model.Detail;
import com.model.DetailType;
import com.service.algorithms.schedule.interfaces.criteria.operation.impl.util.Operation;

import java.util.*;

/**
 * Created by andron94 on 06.05.15.
 */
public class ExpectancyOperationComparator extends Operation implements Comparator<Detail> {
    private Map<DetailType, Double> expectancyFactor = new HashMap<>();

    public ExpectancyOperationComparator( Map<DetailType, Double> expectancyFactor ){
        this.expectancyFactor = expectancyFactor;
    }

    @Override
    public int compare(Detail o1, Detail o2) {
        return expectancyFactor.get(o1.getDetailType()).compareTo(
                expectancyFactor.get(o2.getDetailType())
        );
    }
}
