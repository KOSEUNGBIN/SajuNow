<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>안드로이드 shouldoverrideurlloading 기존창
  <head>
  <meta http-equiv="Content-Type"  content="text/html;charset=UTF-8">
  <title>API방식 Sample Code</title>
  <script type="text/javascript"  src="https://api.paygate.net/ajax/common/OpenPayAPI.js"></script>  <!--  1.API 호출 -->
  <script>

  </script>
  
 
   </head>
   <body>
<div style="width: 100%; height: 22px; border: none; "></div>
   <div id="PGIOscreen">     <!--  4."doTransaction()호출시 PGIOscreen에서 결제창이 뜸 -->
         <!--  2.PGIOForm에서 데이터 입출력 -->  
          
          결제성공                   <br>
  mid : ${payment.mid}   <br>
   unitprice :${payment.unitprice} <br>
  tid : ${payment.tid} <br>
 replycode :  ${payment.replycode} <br>
  replyMsg : ${payment.replyMsg} <br>
 hashresult :  ${payment.hashresult} <br>
   

  </body>
  </html>