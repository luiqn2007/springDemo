<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Fixed Deposit List</title>
    <style>
        .border {
            border: 1px solid;
            border-collapse: collapse;
        }

        .td, .th {
            border: 1px solid;
            font-family: Arial, serif;
            font-size: 12px;
        }

        .a {
            font-family: Arial, serif;
            font-size: 12px;
        }
    </style>
</head>
<body>
<form name="fixedDepositList" method="post" action="${pageContext.request.contextPath}/fixedDeposit/list}">
    <table align="left" style="padding-left: 300px;">
        <tr>
            <td style="font-family: Arial, serif; font-size: 16px; font-weight: bold">Fixed deposit list</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <table class="border" cellpadding="10">
                    <tr bgcolor="#99CCFF">
                        <td class="td">ID</td>
                        <td class="td">Amount</td>
                        <td class="td">Tenure</td>
                        <td class="td">Email</td>
                        <td class="td">Action</td>
                    </tr>
                    <c:forEach items="${fdList}" var="fixedDeposit">
                        <tr class="border">
                            <td class="td"><c:out value="${fixedDeposit.id}"/></td>
                            <td class="td"><c:out value="${fixedDeposit.depositAmount}"/></td>
                            <td class="td"><c:out value="${fixedDeposit.tenure}"/></td>
                            <td class="td"><c:out value="${fixedDeposit.email}"/></td>
                            <td class="td">
                                <a href="${pageContext.request.contextPath}/fixedDeposit/?fdAction=close&fixedDepositId=${fixedDeposit.id}"
                                   style="color: red">Close</a>
                                <a href="${pageContext.request.contextPath}/fixedDeposit/?fdAction=view&fixedDepositId=${fixedDeposit.id}"
                                   style="color: green">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr align="center">
                        <td>
                            <input type="submit" value="Create new Fixed Deposit">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>