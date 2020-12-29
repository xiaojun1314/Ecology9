<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="weaver.conn.RecordSet"%>
<%@ page import="weaver.conn.RecordSetDataSource"%>
<%@ page import="com.weaver.general.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.weaver.file.Prop" %>
<%
   response.setHeader("cache-control", "no-cache");
   response.setHeader("pragma", "no-cache");
   response.setHeader("expires", "Mon 1 Jan 1990 00:00:00 GMT");
   RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
   String gsmcVal = Util.null2String(request.getParameter("gsmcVal"));
   String ksbmVal = Util.null2String(request.getParameter("ksbmVal"));
   String sql1="select custtype,custname from cust_supplier_v  where custcode='"+ksbmVal+"' and gsbm='"+gsmcVal+"'";
   rsnc.execute(sql1);
   rsnc.next();
   String custtype=Util.null2String(rsnc.getString("custtype"));
   String custname=Util.null2String(rsnc.getString("custname"));
   JSONObject obj = new JSONObject();
   obj.put("kslxVal",custtype);
   obj.put("ksmcVal",custname);
   out.print(obj.toString());
%>
