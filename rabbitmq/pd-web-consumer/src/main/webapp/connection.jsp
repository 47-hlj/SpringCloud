<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>������ת...</title>
</head>

<!--  -->
<body onload="javascript:document.forms[0].submit()">  
   <h1>������</h1>
    <%-- �ױ�����ʽ���أ�https://www.yeepay.com/app-merchant-proxy/node --%>  
    <%-- �ױ��Ĳ������أ�http://tech.yeepay.com:8080/robot/debug.action --%>  
    <form action="https://www.yeepay.com/app-merchant-proxy/node" method="Post" name="yeepay">  
        <!-- ����hidden�е�nameֵΪ�ױ�֧���淶�Ĺ̶�������˳�� -->  
        <input type='hidden' name='p0_Cmd' value="${businessType}">  
        <input type='hidden' name='p1_MerId' value="${accountID}">  
        <input type='hidden' name='p2_Order' value="${orderID}">  
        <input type='hidden' name='p3_Amt' value="${amount}">  
        <input type='hidden' name='p4_Cur' value="${currency}">  
        <input type='hidden' name='p5_Pid' value="${productID}">  
        <input type='hidden' name='p6_Pcat' value="${productCategory}">  
        <input type='hidden' name='p7_Pdesc' value="${productDesc}">  
        <input type='hidden' name='p8_Url' value="${accountCallbackURL}">  
        <input type='hidden' name='p9_SAF' value="${addressFlag}">  
        <input type='hidden' name='pa_MP' value="${accountMoreInfo}">  
        <input type='hidden' name='pd_FrpId' value="${accountBankID}">  
        <input type="hidden" name='pr_NeedResponse' value="${needResponse}">  
        <input type='hidden' name='hmac' value="${md5hmac}">  
    </form>  
</body>  
</html>