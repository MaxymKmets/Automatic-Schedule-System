package com.repository;

import com.model.Contract;
import com.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 18.04.15.
 */
public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {
    public List<Order> findByUser_Email(String email);
    public Order findOneByIdAndUser_Email(int orderId,String email);
    public List<Order> findByStatus_Name(String statusName);

    @Query("select distinct o from Order as o inner join" +
            " o.status as s inner join fetch" +
            " o.contractList as c where s.name=:pending")
    public List<Order> findAllOrdersForPlane(@Param("pending") String pending);
    @Query("select distinct o from  Order as o inner join " +
            " o.contractList as c inner join " +
            " o.status as s " +
            "where c.start_date<:timeStep and s.id=2")
    public List<Order> findAllThatMustProcessing(@Param("timeStep") Date timeStep);

    @Query("select distinct o from  Order as o inner join" +
            " o.status as s" +
            " where o.deadilne<= :timeStep and s.name='NEW_ORDER'")
    public List<Order> findAllThatMustDecline(@Param("timeStep") Date timeStep);

    @Query("select distinct o from  Order as o inner join" +
            " o.contractList as c inner join" +
            " o.status as s" +
            " where :timeStep >=" +
                "( select max(k.end_date) from Contract k " +
                 "where k.order.id=o.id)" +
            " and s.id=3")
    public List<Order> findAllThatMustDone(@Param("timeStep") Date pending);

    @Query("select sum(t.produceTime) from Order as o inner join" +
            "  o.computer as c inner join c.detailList k inner join k.detailType t " +
            " where o.id=:idOrder")
    public long findPerformanceTime(@Param("idOrder") int idOrder);

    @Query("select distinct o from Order as o inner" +
            " join o.status as s " +
            "where  s.name=:procesing  ")
    public List<Order> findByStartAfterAndOrderStatusName(@Param("procesing")String statusName);
}


