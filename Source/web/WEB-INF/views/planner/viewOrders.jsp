
<%--
  Created by IntelliJ IDEA.
  User: andron94
  Date: 18.04.15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
  <c:choose>
    <c:when test="${not empty page.getItems()}">
      <table class="table">
        <thead>
        <tr>
          <td><strong>ID</strong></td>
          <td><strong>Client</strong></td>
          <td><strong>Status</strong></td>
          <td><strong>Action</strong></td>
        </tr>
        </thead>
        <c:forEach var="order" items="${page.getContent()}">
          <c:choose>
            <c:when test="${order.getStatus().getName()=='NEW_ORDER'}">
              <c:set var="orderStatus" value="New order" />
              <c:set var="rowStyle" value="info" />
              <c:set var="btnStyle" value="btn btn-primary" />
              <c:set var="btnName" value="Plan schedule" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='IN_QUEUE'}">
              <c:set var="orderStatus" value="Pending" />
              <c:set var="rowStyle" value="warning" />
              <c:set var="btnStyle" value="btn btn-success" />
              <c:set var="btnName" value="View order" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='IN_PROSESS'}">
              <c:set var="orderStatus" value="Processing" />
              <c:set var="rowStyle" value="warning" />
              <c:set var="btnStyle" value="btn btn-success" />
              <c:set var="btnName" value="View order" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='DONE'}">
              <c:set var="orderStatus" value="Success" />
              <c:set var="rowStyle" value="success" />
              <c:set var="btnStyle" value="btn btn-success" />
              <c:set var="btnName" value="View order" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='SYSTEM_CANCEL'}">
              <c:set var="orderStatus" value="Declined" />
              <c:set var="rowStyle" value="error" />
              <c:set var="btnStyle" value="btn btn-danger" />
              <c:set var="btnName" value="Send propose" />
            </c:when>
            <c:when test="${order.getStatus().getName()=='USER_CANCEL'}">
              <c:set var="orderStatus" value="Canceled by client" />
              <c:set var="rowStyle" value="error" />
              <c:set var="btnStyle" value="btn disabled" />
              <c:set var="btnName" value="Canceled" />
            </c:when>
          </c:choose>
          <tr class="${rowStyle}">
            <td>${order.getId()}</td>
            <td>Unknown</td>
            <td>${orderStatus}</td>
            <td><button class="${btnStyle}">${btnName}</button></td>
          </tr>
        </c:forEach>
      </table>
      <%--Pagination Bar--%>
      <div class="pagination pagination-centered">
        <ul>
          <c:forEach var="pageItem" items="${page.getItems()}">
            <c:url var="pageUrl" value="./${pageItem.getNumber()}" />

            <c:choose>
                <c:when test="${pageItem.isCurrent()==true}">
                  <li class="active">
                    <a href="${pageUrl}">${pageItem.getNumber()}</a>
                  </li>
                </c:when>
                <c:otherwise>
                  <li>
                    <a href="${pageUrl}">${pageItem.getNumber()}</a>
                  </li>
                </c:otherwise>
              </c:choose>
          </c:forEach>
        </ul>
      </div>
    </c:when>
    <c:otherwise>
      <h1>Sorry, but . . . all orders, gone . . . =(</h1>
    </c:otherwise>
  </c:choose>
</div>
