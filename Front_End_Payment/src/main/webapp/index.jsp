<%@page import="com.Payment" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

      <link rel="stylesheet" href="Views/bootstrap.min.css">
      <script src="Components/jquery-3.6.0.min.js"></script>
      <script src="Components/main.js"></script>

</head>
<body style="background-color:#e3ebea;">
   
   
    <br/>   
    <div class="container" style="background-color:#ffffff; width:60%">
    <br/> 
    
     <h3 class="text-body" style="text-align:center;">Payment Service </h3>             
     
    <br/>  
   <div class="container" style=" width:50%">
    <form id="formPayment" name="formPayment">

      <div class="form-group" >
        <label> Bill ID </label>  <input type="text" name="billID" id="billID" class="form-control"  placeholder="1" >
      </div>  
     <div class="row"> 
       <div class="col"> 
         <div class="form-group" >
            <label> Payment Amount </label> <input type="text"  name="paymentAmount" id="paymentAmount" class="form-control"  placeholder="500.00" >
         </div>   
       </div>  
       <div class="col">
         <div class="form-group" >
            <label>CardType </label> <input type="text"  name="creditCardType" id="creditCardType" class="form-control"  placeholder="visa" >  
         </div>
       </div>
     </div>         
       
     
     <div class="row"> 
       <div class="col"> 
        <div class="form-group" >
          <label>Expire Month</label> <input type="text" name="expirationalMonth" id="expirationalMonth" class="form-control"  placeholder="January">
        </div>
      </div>
      <div class="col">
        <div class="form-group" >
          <label>Expire Year</label> <input type="text"  name="expirationalYear" id="expirationalYear" class="form-control"  placeholder="2024">
        </div>
      </div>
    </div>    
    <div class="row"> 
      <div class="col"> 
          <div class="form-group" >
             <label>Card No</label> <input type="text" name="cardNo" id="cardNo" class="form-control"  placeholder="1234-5678-1234-5678">
          </div>
      </div>
      <div class="col">     
           <div class="form-group" >
             <label>CVV </label> <input type="text"  name="cvn" id="cvn" class="form-control"  placeholder="434">
          </div>
     </div>
   </div>       
   <br/>  
   
    <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-warning" style="width:100%">
    <input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
    
   
</form>
     <br/>
    <div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>
     <br/> 
 </div> 
 </div>  
 <div class="container" style="background-color:#ffffff; width:60%">
   <br/> 
	<center> <div id="divUsersGrid">
       <%
           Payment payObj = new Payment();
           out.print(payObj.readPaymentDetails());
       %>
    </div>
   </center> 
     <br>
    <br>
   
   </div>
   <br/> <br/>
   <br/>
</body>
</html>