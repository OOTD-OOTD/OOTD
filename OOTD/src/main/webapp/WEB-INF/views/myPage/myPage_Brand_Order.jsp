<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>브랜드 - 주문 내역</title>
	    <script src="${pageContext.request.contextPath }/resources/asset/js/jquery-3.6.0.min.js"></script>
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/myPage.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/order_Detail.css">
	</head>
<body>

	<!-- HEADER IMPORT -->
	<c:import url="../common/header.jsp"/>

    <div class="container-myPage">
        <div class="body-title">MY PAGE</div>
        
        <!-- 좌측 aside 메뉴 (BRAND)-->
        <%@ include file="myPage_Brand_Menu.jsp" %>
        
        <section class="main">
            <div class="wrap-section">
                <div class="table-title">Order List</div>
                <table class="order-list">
                    <thead>
                        <tr>
                            <th>상품명</th>
                            <th>수량</th>
                            <th>주문번호</th>
                            <th>주문일자</th>
                            <th>판매금액</th>
                            <th>주문상태</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${list}" var="o"> 
                    <tbody>
                        <tr>
                            <td>
                                <div class="product-info">
                                    <span class="product-name">${o.product_name}</span>
                                    <span class="product-option">(small)</span>
                                </div>
                            </td>
                            <td>
                                <div class="order-quantity">${o.order_quantity}</div>
                            </td>
                            <td>
                                <span class="order-no">${o.order_no}</span>
                            </td>
                            <td class="order-date">${o.order_date}</td>
                            <td class="total-price">${o.total_price}&nbsp;&#8361;</td>
                            <td>
                                <span class="purchase-status">1</span> <!-- 값(숫자)에 따라 주문상태, 버튼 변경-->
                            </td>
                            <td>
                                <button class="complete-send">발송 완료</button>
                            </td>
                        </tr>                                    
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </section>
    </div>

   	<!-- Order Detail(Mordal) -->
    <%@ include file="myPage_Order_Detail.jsp"%>
    
    
        
    <!-- FOOTER IMPORT -->
   	<%@ include file="../common/footer.jsp" %>
   	
</body>

<script>
    // 모달창 스크립트
    $('.order-no').click(function () {
        $('.modal').fadeIn();
    })

    $('.modal').on('click', function (event) {
        if ($(event.target).hasClass('modal')) {
            $('.modal').fadeOut()
        }
    });

    $('.close-icon').click(function () {
        $('.modal').fadeOut();
    })

    // .purchase-status의 값(value)에 따라 배송 상태 텍스트 변경
    $(function () {
        $('.purchase-status').each(function () {

            var ps = $(this).text();

            compSend = $(this).parent().next().children('.complete-send');

            switch (ps) {
                case "1":
                    $(this).text('결제완료');
                    compSend.show();
                    break;
                case "2":
                    $(this).text('구매확정대기');
                    break;
                case "3":
                    $(this).text('거래완료');
                    break;
            }
        })
    })



</script>

</html>