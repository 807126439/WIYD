package com.wb.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wb.core.utils.pojo.DataTableHead;


/**
 * Datatables 表头生成器
 * @author wb_java_zjr
 *
 */
public class DatatablesHeaderTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256292152140255160L;
	private List<DataTableHead> headInfos;
	private String varName;
	private String editVarName;
	private String delVarName;
	

	public int doStartTag() throws JspException {
		
	
		
		return EVAL_PAGE;
	}
	
	
	

	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		
		try {
			out.print(end());
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	
		
		return EVAL_PAGE;
	}

	
	private String ID_HEADER = "{\"mDataProp\":\"id\" ,\"bSortable\": false,"+
			    			   	  "\"mRender\": function (data, type, full) {"+
			    			   	  " return \"<input type=\\\"checkbox\\\" class=\\\"checkbox\\\" value=\\\"\"+data+\"\\\">\";}}";
			    			   	  

	
	public String end(){	
			   	  		
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("var "+varName+"=[];");
		sb.append(varName+".push("+ID_HEADER+");");
		for (DataTableHead head : headInfos) {
			sb.append(varName+".push({\"sTitle\": \""+head.getsTitle()+"\", \"bSortable\": "+head.getbSortable()+",\"mDataProp\":\""+head.getmDataProp()+"\" });");
		}
	
		sb.append(varName+".push(");
		sb.append("{\"sTitle\": \"操作\",\"width\":\"120px\", \"mDataProp\":\"id\" ,\"bSortable\": false,");
		sb.append("\"mRender\": function (data, type, full) { var html = \"\";");
		sb.append("if(typeof("+editVarName+")!= \"undefined\"){  if("+editVarName+".length>0){ html+="+editVarName+".format(data);}} ");
		sb.append("if(typeof("+delVarName+")!= \"undefined\"){  if("+delVarName+".length>0){ html+="+delVarName+".format(data);}} ");           			 
        sb.append("return html;} }");               			 	
        sb.append(");");              		
		
		sb.append("</script>");
		

		return sb.toString();
		
	}




	public List<DataTableHead> getHeadInfos() {
		return headInfos;
	}




	public void setHeadInfos(List<DataTableHead> headInfos) {
		this.headInfos = headInfos;
	}




	public String getVarName() {
		return varName;
	}




	public void setVarName(String varName) {
		this.varName = varName;
	}




	public String getEditVarName() {
		return editVarName;
	}




	public void setEditVarName(String editVarName) {
		this.editVarName = editVarName;
	}




	public String getDelVarName() {
		return delVarName;
	}




	public void setDelVarName(String delVarName) {
		this.delVarName = delVarName;
	}
	
	

	
	
	


	
	
	
	
	
	
	
}
