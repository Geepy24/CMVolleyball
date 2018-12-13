<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${artTitle}</title>
</head>
<body>
	<s:debug></s:debug>
<table style="width:500 ;border: 0; align:center;cellpadding:0;cellspacing:2">
    <tr>   
        <td align="center"><h1>${article.artTitle}</h1></td> 
    </tr>
    <tr>   
        <td align="center">${article.authorName }  </td>
    </tr>
    <tr>
        <td>${article.artContent }</td>
    </tr>
<tr>
<td>${article.pubTime}</td>
</tr>
 
</table>
</body>
</html>