 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<title>Sajunow 결제</title>
</head>
<body> 

<div style="width: 100%; height: 22px; border: none; "></div>
<form name="PGIOForm" action="https://service.paygate.net/openAPI.jsp" method="POST">
<table border="0">

<tr>
<td><input type=hidden size="10" name="mid" value="paygatekr"/></td></tr>

<tr>
<td><input type=hidden size=8 name="langcode" value="KR">
	<input type=hidden size=8 name="charset" value="UTF-8"/></td></tr>

<tr><th><b>결제수단</b></th>
<td><select name=paymethod>
			<option value="card" selected>신용카드</option>
		</select></td></tr>

<tr><th><b>결제금액</b></th>
<td><input size=10 name="unitprice" value="1000" readonly/>원
			<input  type=hidden name = goodcurrency value="WON"/></td></tr>

<tr><th><b>상담인</b></th><td><input size="26" name="goodname" value="${company_name}" readonly/></td></tr>
<tr><th><b>이름</b></th><td><input size="26" name="receipttoname" value= "${user.name}" readonly/></td></tr>
<tr><th><b>이메일</b></th><td><input size="26" name="receipttoemail" value= "${user.email}"/></td></tr>

<tr><td><input  type=hidden size="26" name="tid" value=""/></td></tr>
<tr><td><input  type=hidden size="26" name="redirecturl" value=""/></td></tr>
<tr><td><input  type=hidden size="26"  name="replycode" value=""/></td></tr>
<tr><td><input type=hidden size="26" name="replyMsg" value=""/></td></tr>
<tr><td><input type=hidden size="26" name="profile_no" value=""/></td></tr>
<tr><td><input type=hidden size="26" name="hashresult" value="test1"/></td></tr> 
<tr><td><input type=hidden type=text size="26" name=riskscore value=""></td></tr>
<input type=hidden name=cardtype value="">
<input type=hidden name=cardnumber value="">
<input type=hidden name=cardauthcode >
<input type=hidden name=cardsecretnumber size=3 value="">
<input type=hidden name=cardexpiremonth size=2 value="">
<input type=hidden name=cardexpireyear size=4 value="">
<tr></td></tr>

</table>
<input type=submit value="결제하기">
  </form>
  
  <div class="container">
  <h2>사주나우 결제</h2>
  <br>
 <form name="PGIOForm" action="https://service.paygate.net/openAPI.jsp" method="POST">
 
 <input type=hidden size="10" name="mid" value="paygatekr"/>
 <input type=hidden size=8 name="langcode" value="KR">
 <input type=hidden size=8 name="charset" value="UTF-8"/>
 <input  type=hidden size="26" name="tid" value=""/>
 <input  type=hidden size="26" name="redirecturl" value=""/>
 <input  type=hidden size="26"  name="replycode" value=""/>
 <input type=hidden size="26" name="profile_no" value=""/>
 <input type=hidden size="26" name="hashresult" value="test1"/>
 <input type=hidden type=text size="26" name=riskscore value="">
 <input type=hidden name=cardtype value="">
<input type=hidden name=cardnumber value="">
<input type=hidden name=cardauthcode >
<input type=hidden name=cardsecretnumber size=3 value="">
<input type=hidden name=cardexpiremonth size=2 value="">
<input type=hidden name=cardexpireyear size=4 value="">
<input  type=hidden name = goodcurrency value="WON"/>

<div class="grid-35 pull-65 app-grid-70 app-pull-0 mobile-grid-100" id="checkout-pay-with">
<fieldset class="pay-with">
<div class="control-group">
<label class="control-label" for="payment-option">아래의 결제 수단으로 결제합니다.</label>
<div class="controls">
<select tabindex="1" id="payment-option" name="paymentOption" class="input-block" required="required">
<option data-placeholder="placeholder" value="/shop/ko/checkout/pay/p-817770da-21069-0">결제 수단을 선택해주세요.</option>
<option value="/pay/openapi" >
신용카드
</option>
<option value="/pay/openapi" >
무통장입금
</option>
</select>
</div>
</div>
</fieldset>
</div>
 
      <label for="email">결제수단 : </label>
      <select name=paymethod>
			<option value="card" selected>신용카드</option>
		</select>
    
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input size=10 name="unitprice" value="1000" readonly/>원
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>


 </body>
</html>
 --%>
 
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<title>사주나우 결제</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value='/resources/js/availableChk.js' />"></script>

<!-- <script type="text/javascript" src="https://api.paygate.net/ajax/common/OpenPayAPI.js"></script> -->
<script type="text/JavaScript">

<!--

function change_method(f) {
    var app = navigator.userAgent;  // MSIE, Firefox, Netscape, Opera
    var info1 = document.getElementById("id_cardInfoTbl");
    var info2 = document.getElementById("id_bankInfoTbl");
		var info3 = document.getElementById("id_cardInfoTbl_DIV");
    
    if (f.pay_method[0].checked==true) {
       info1.style.display = 'table-row';
			 info3.style.display = 'table-row';
			 document.getElementById("td0").style.backgroundColor="#ffff66";
			 document.getElementById("td1").style.backgroundColor="#FFFFFF";
       info2.style.display = 'none';
    } else if (f.pay_method[1].checked==true) {
        document.payForm.pay_method.value="B";
		    info2.style.display = 'table-row';
        info1.style.display = 'none';
				info3.style.display = 'none';
			  document.getElementById("td0").style.backgroundColor="#FFFFFF";
				document.getElementById("td1").style.backgroundColor="#ffff66";
    }
}


function startPayment() {
																
    var f = document.forms['PGIOForm'];
   /*  if (!f.cardtype.value) {
        alert('Please select Caredit Card Type. ');
        f.cardtype.focus(); return false;
    } */
    /* if (!f.cardnumber.value) {
        alert('Please enter Card Number. '); f.cardnumber.focus(); return false;
    }
    if (!f.receipttoname.value) {
        alert('Please enter Card Holder\'s Name. ');
        f.receipttoname.focus(); return false;
    } */
    
    alert('Billing process will start shortly.\n\nPlease click enter and wait.');
    document.getElementById('carebtn').style.display="none";    
    doTransaction(document.PGIOForm);
}

function startPaymentKr() {
		
    var f = document.forms['PGIOForm'];
    /* if (!f.cardtype.value) {
        alert('Please select Caredit Card Type. ');
        f.cardtype.focus(); return false;
    } */
    
    alert('곧 결제가 진행됩니다. \n\n확인을 누른 후 잠시만 기다려 주세요.  ');
    document.getElementById('carebtn').style.display="none"; 
    doTransaction(document.PGIOForm);
}


function getPGIOresult() {
    var replycode = getPGIOElement('replycode')
    var replyMsg = getPGIOElement('replyMsg')
    //var replycode = document.PGIOForm.elements['replycode'].value;
    //var replyMsg = document.PGIOForm.elements['replyMsg'].value;
    
    //displayStatus(getPGIOElement('ResultScreen'));  //
    
    if (replycode == '0000') {
        // 
        document.PGIOForm.action = 'reg_payment_proc.asp?pLn=&sMenu=';
        document.PGIOForm.method = 'post';
        document.PGIOForm.submit();
    } else {
        // 
        //alert("Pament error. Please try again.");
        alert('[Error No. '+replycode+']\n\n ' + replyMsg);
        document.PGIOForm.unitprice.value = '725';
        //document.PGIOForm.cardtype.selectIndex = 0;
        //document.location.reload();
        
    }
}


function saveTransferInfo() {
    var f = document.forms['payForm'];
    if (!f.senderName.value) {
        alert('Please enter Sender\'s Name');
        f.senderName.focus(); return false;
    }
    f.action = 'reg_payment_proc.asp?pLn=&sMenu=';
    f.method = 'post';
    f.submit();
}
//-->
</script>
</head>

<body onLoad="change_method(document.forms['methodForm']);">
<div style="width: 100%; height: 22px; border: none; "></div>
<div id="wrapper">
  
<div id="main">
<ul>
<h2>
사주나우 결제</h2>
</ul>
<div id="nav2">

</div>
<div style="clear:both">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" align="center">&nbsp;</td>
    </tr>
    <tr>                                   
      <td height="25" align="center"><img src=<c:url value="/resources/images/paygate.png"/> width="500" height="158" /></td>
    </tr>
    <tr>
      <td class="title_01">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td class="txt">
      
      &bull; 결제 수단을 선택해주세요.<br /> 
&bull; 상담은 사주나우 Android App에서 진행할 수 있습니다.<br />
&bull; 결재가 완료되면 역술인과의 채팅방이 개설됩니다.<br />
&bull; 상담은 역술인이 종료할 때까지 이용할 수 있습니다.<br />
&bull; 웹페이지 결제는 IE에서만 가능합니다.<br />

</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>
      <form name="methodForm">
      <table width="100%"  style="border:1px solid #ccc;" border="0" cellspacing="0" cellpadding="0" >
        <tr>                                                    
                        <td align="center" id="td0" width="50%" style='padding:10px;'><img src=<c:url value="/resources/images/c_icon.gif"/> width="50" height="41" /><br />
                          <input type="radio" name="pay_method" id="pay_method" value="C" onClick="change_method(this.form);"  />
                          신용 카드 </td>
                        <td align="center"  id="td1" width="50%" style='padding:10px;'><img src=<c:url value="/resources/images/b_icon.gif"/> width="50" height="39" /><br />
                          <input type="radio" name="pay_method" id="pay_method" value="B" onClick="change_method(this.form);"  />
                          무통장 입금</td>
                      </tr>
      </table>
      </form>
      </td>
    </tr>
    
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
    
    <!-----##########################################################################--->   
    <tr id="id_cardInfoTbl"  style="display:none;">
          <td valign="top">
          
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
          
              
              <tr>
                <td>
    
      
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                    
                    <tr>
                      <td><!--Start. CREDIT CARD -->
                      
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_1">
                          <tr>
                            <th width="40%" bgcolor="#F6F6F6">상담인</th>
                            <td >${company_name}</td>
                          </tr>
                          <tr>
                            <th width="40%" bgcolor="#F6F6F6">결재자</th>
                            <td >${user.name}</td>
                          </tr>
                          <tr >
                            <th  bgcolor="#F6F6F6"> 결재금액</th>
                            <td >
                            30,000원                            </td>
                          </tr>
                          
                        </table>
                        
                    
            
            
                        <!--End. CREDIT CARD -->
                      </td>
                    </tr>
                    <tr id="id_cardInfoTbl_DIV">
                    <td align="center" bgcolor="#FFFFFF"><div id="PGIOscreen"></div></td>
                    </tr>
                  </table>
                  <form name="PGIOForm" action="https://service.paygate.net/openAPI.jsp" method="POST">
 
 <input type=hidden size="10" name="mid" value="nowlab"/>
 <input type="hidden" name="goodcurrency" value="WON">
 <input type=hidden size=8 name="langcode" value="KR">
 <input type=hidden size=8 name="charset" value="UTF-8"/>
 <input type="hidden" name="goodname" value="${company_name}"/>
 <input type="hidden" name="unitprice" value="1000" />
 <input type="hidden" name="receipttoemail" value="positiwee@gmail.com" maxlength="101">
 <input type="hidden" size="26" name="receipttoname" value= "${user.name}"/>
 <input  type=hidden size="26" name="tid" value=""/>
 <input  type=hidden size="26" name="redirecturl" value=""/>
 <input  type=hidden size="26"  name="replycode" value=""/>
 <input type=hidden size="26" name="profile_no" value=""/>
 <input type=hidden size="26" name="hashresult" value="nowlab!"/>
 <input type=hidden type=text size="26" name=riskscore value="">
 <input type=hidden name=cardtype value="">
<input type=hidden name=cardnumber value="">
<input type=hidden name=cardauthcode >
<input type=hidden name=cardsecretnumber size=3 value="">
<input type=hidden name=cardexpiremonth size=2 value="">
<input type=hidden name=cardexpireyear size=4 value="">
<input  type=hidden name = goodcurrency value="WON"/>
<input  type=hidden name = paymethod value="card"/>
<div style="width: 100%; height: 15px; border: none; "></div>
<button type="submit" align="center" class="btn btn-primary">결제</button>
                  
                 <!--  <form name="PGIOForm">	
                  
                  <input type="hidden" name="mid" value="isoc2016us" />
                  <input type="hidden" name="goodcurrency" value="USD">
                  <input type="hidden" name="langcode" value="US" />
                  <input type="hidden" name="paymethod" value="104" />
                  
                  <input type="hidden" name="goodname" value="ISOC 2016" />
                  <input type="hidden" name="unitprice" value="725" />
                  <input type="hidden" name="receipttoemail" value="positiwee@gmail.com" maxlength="101">
                  <input type="hidden" name="charset" value="UTF-8">
                                  
                  <input type="hidden" name="tid" value="">
                  <input type="hidden" name="mb_serial_no" value="">
                    
                  <input type="hidden" name="cardquota" value="00" />할부기간
                  <input type="hidden" name="cardownernumber" />소유자주민뒷번호
                  <input type="hidden" name="cardauthcode" />승인번호
                  <input type="hidden" name="replycode" value="" />결과
                  <input type="hidden" name="replyMsg" value="" />메세지
              		<input name="receipttoname" type="hidden" value="E_1046">
                  <input type="hidden" name="payIdx" value="1046" />-->
                  
                  </form> 
                </td>
              </tr> 
              <tr><td height="20">&nbsp;</td></tr>  
              
        
              
            </table>
            
                          
                          <!-- End. card -->
                          
      
          </td>
     </tr>
     <!-----##########################################################################--->   
     
     <form name="payForm">
          <input type="hidden" name="pay_method" value="B">
          <input type="hidden" name="pay_amount" value="725">
          <input type="hidden" name="pay_unit" value="USD">
          <input type="hidden" name="payIdx" value="1046" />
        
        <tr id="id_bankInfoTbl"  style="display:none;">
          <td>
        
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
        
           
             
            
              <tr>
                <td class="height">&nbsp;</td>
              </tr>
              <tr>
                <td>
                  
                  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
                    <tr>
                      <td height="25" align="left" >
                      무통장입금

                      
                      </td>
                    </tr>
                    <tr>
                      <td height="1" bgcolor="#FFFFFF"></td>
                    </tr>
                    <tr>
                      <td><!--Start. Bank Transfer -->
                      
                        
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_1">
                          <tr>
                            <th width="20%" bgcolor="#F6F6F6">결재자</th>
                            <td width="30%" >${user.name}</td>
                            <th width="20%" bgcolor="#F6F6F6">은행명</th>
                            <td width="30%" >신한은행</td>
                          </tr>
                          <tr >
                            <th  bgcolor="#F6F6F6">결재금액</th>
                            <td >30,000원</td>
                             <th  bgcolor="#F6F6F6">계좌번호</th>
                            <td > 792-548448-441</td>
                          </tr>
                         
                          
                        </table>
                        
            
                      <!--End. Bank Transfer -->
                      </td>
                    </tr>
                  </table>
      
                </td>
              </tr>
              <tr>
                <td class="height">&nbsp;</td>
              </tr>
              
            </table>
          
          </td>
        </tr>
        </form>
        <!-----##########################################################################--->   
        
          
    <tr>
      <td height="25">&nbsp;</td>
    </tr>

   
        </table></td>
    </tr>
    <tr>
      <td height="43">&nbsp;</td>
    </tr>
   
    <tr>
      <td height="37">&nbsp;</td>
    </tr>
    </table>
</div>
</div>


</div>
</body>
</html>
 