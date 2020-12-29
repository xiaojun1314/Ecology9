<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="weaver.conn.*"%>
<%@ page import="java.math.*"%>
<%@ page import="java.util.*,weaver.general.*"%>
<%@ page import="java.sql.*,weaver.interfaces.datasource.DataSource"%>
<script type="text/javascript">
    <% String wfid = request.getParameter("workflowid");
    int requestid = Util.getIntValue(request.getParameter("requestid"));
                RecordSet rs = new RecordSet();
                rs.execute("select * from workflow_base where id = " + wfid);
                System.out.println("=================wfid:" + wfid);
                rs.next();
                rs.execute("select 'var '||t1.fieldname||replace(t1.detailtable,'formtable_main_'||replace(to_char(t1.billid),'-',''))||' = '''||t1.id||''''||';//'||t2.indexdesc||'\t\n' declare from workflow_billfield t1,Htmllabelindex t2 where billid = "
                        + rs.getInt("formid") + " and t1.fieldlabel = t2.id");
                while (rs.next()) {%>
    <%=rs.getString("declare").toLowerCase()%>
    <%}%>
</script>
<script type="text/javascript"  src="/zdn/gyl/zsj/xxks/ksxzyh_script.js"></script>