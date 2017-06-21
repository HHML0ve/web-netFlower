<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.Items"%>
<%@page import="dao.ItemsDao" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示页面</title>
<style type="text/css">
	   hr{
	    border-color:FF7F00; 
	   }
	   div{
	      float:left;
	      margin: 10px;
	   }
	</style>
</head>
<body>
<h1>商品展示</h1>
<hr>
<strong>欢迎${ userName}</strong>
 <center>
 <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
    <tr>
    <td>
     <%
     		   ItemsDao itemsDao = new ItemsDao(); 
               ArrayList<Items> list = itemsDao.getAllItems();
               if(list!=null&&list.size()>0)
               {
	               for(Items item:list)
	               {
           %>   
          <div>
		<a href="details.jsp?id=<%=item.getId()%>"><img alt="商品" src="images/<%=item.getPicture()%>" width="120" height="90"></a><br>
		<%=item.getName() %><br>
		产地：<%=item.getCity() %>价格：<%=item.getPrice() %><br>
	</div>
          <!-- 商品循环结束 -->
        
          <%
                   }
              } 
          %>
        </td>
      </tr>
    </table>
    </center>
</body>
</html>