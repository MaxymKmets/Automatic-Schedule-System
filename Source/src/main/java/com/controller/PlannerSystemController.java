package com.controller;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXTimelineUnit;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.model.Order;
import com.model.User;
import com.service.OrderService;
import com.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerSystemController {
    @Autowired
    private OrderService orderService;

    //view orders page
    @RequestMapping(value = "/viewOrders/{pageNumber}",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(@PathVariable Integer pageNumber){
        PageWrapper<Order> page = new PageWrapper<Order>(
            orderService.getAllOrders(pageNumber)
        );
        return new ModelAndView("viewOrders","page",page);
    }

    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Order order = orderService.getOrderById(orderId);
        return new ModelAndView("viewOrder","order",order);
    }

    @RequestMapping(value = "schedule",method = RequestMethod.GET)
    public ModelAndView scheduleAction(HttpServletRequest request)throws Exception{
            DHXPlanner s = new DHXPlanner("/resources/codebase/", DHXSkin.TERRACE);
            s.setWidth(900);
            s.setInitialDate(2013, 0, 21);
            s.views.clear();
            DHXTimelineView view = new DHXTimelineView( "event_type", "Type");
            view.setRenderMode(DHXTimelineView.RenderModes.CELL);
            view.setXScaleUnit(DHXTimelineView.XScaleUnits.MINUTE);
            view.setXStep(30);
            view.setXSize(24);// (8PM - 8AM)/30min
            view.setXLength(30);
            view.setServerList("event_type");
            s.views.add(view);
            for(int i= 0; i<15;i++){
                view.addOption(new DHXTimelineUnit(i, "Worker "+i));
            }
            s.setInitialView("event_type");
        request.setAttribute("planner",s.render());
        return  new ModelAndView("schedule");
    }
}

