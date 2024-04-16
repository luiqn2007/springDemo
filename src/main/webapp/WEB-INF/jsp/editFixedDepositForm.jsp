<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create a new fixed deposit</title>
    <style>
        .td {
            font-family: Arial, serif;
            font-size: 12px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<form name="editFixedDepositForm" method="POST"
      action="${pageContext.request.contextPath}/fixedDeposit?fdAction=edit">
    <table align="left" style="padding-left: 300px;">
        <tr>
            <td style="font-family: Arial, serif; font-size: 16px; font-weight: bold;" align="left">Open Fixed deposit
            </td>
        </tr>
        <tr align="left">
            <td>
                <table class="border" cellpadding="10">
                    <tr>
                        <td class="td"><b>Id:</b></td>
                        <td class="td">
                            <input type="text" name="id" value="${requestScope.fixedDepositDetails.id}" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td"><b>Amount (in USD):</b></td>
                        <td class="td" style="color: #C11B17">
                            <input type="text" name="depositAmount"
                                   value="${requestScope.fixedDepositDetails.depositAmount}"/>
                            <c:out value="${requestScope['error.depositAmount']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td"><b>Tenure (in months):</b></td>
                        <td class="td" style="color: #C11B17">
                            <input type="text" name="tenure" value="${requestScope.fixedDepositDetails.tenure}"/>
                            <c:out value="${requestScope['error.tenure']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td"><b>Email:</b></td>
                        <td class="td" style="color: #C11B17">
                            <input type="text" name="email" value="${requestScope.fixedDepositDetails.email}"/>
                            <c:out value="${requestScope['error.email']}"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr align="left">
            <td>
                <table style="padding-left: 100px;">
                    <tr align="center">
                        <td class="td"><input type="submit" value="Save"/>
                            &nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/fixedDeposit/list" style="color: green">
                                <b>Go Back</b>
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

