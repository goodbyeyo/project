package user.used;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import user.member.MemberVO;
import user.used.UsedVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.File;
import java.io.Reader;
import java.io.IOException;

public class deleteAction extends ActionSupport {
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private UsedVO paramClass; // �Ķ���͸� ������ ��ü
	private UsedVO resultClass; // ���� ��� ���� ������ ��ü
	
	private Used_CommentVO cparamClass = new Used_CommentVO();
	private Used_CommentVO cresultClass = new Used_CommentVO();
	private MemberVO member;
	
	private int currentPage; // ���� ������
	private String fileUploadPath = "C:\\Real\\Eoullim\\WebContent\\upload\\";
	private int uboard_no;
	private int ucomment_no;
	private String member_id;
	
	public deleteAction() throws IOException{
		
		reader = Resources.getResourceAsReader("sqlMapConfig.xml"); // sqlMapConfig.xml ������ ���������� �����´�.
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader); // sqlMapConfig.xml�� ������ ������ sqlMapper ��ü ����.
		reader.close();
	}
	
	// �Խñ� ����
	public String execute() throws Exception {
		member_id = (String)ActionContext.getContext().getSession().get("member_id");
		member = (MemberVO) sqlMapper.queryForObject("checkid",member_id);
		
		// �Ķ���͸� ������ ��ü�� ���� ������� ������ ��ü ����
		paramClass = new UsedVO();
		resultClass = new UsedVO();
		
		// �ش� ��ȣ�� ���� �����´�
		resultClass = (UsedVO) sqlMapper.queryForObject("selectOneUboard", getUboard_no());
		
		// ������ ����� ���� ����
		File deleteFile = new File(fileUploadPath + resultClass.getUboard_file_savname());
		deleteFile.delete();
		
		// ���� �� �׸� ����
		paramClass.setUboard_no(getUboard_no());
		
		// �Խñ� ���� ���� ����
		sqlMapper.update("deleteUboard", paramClass);
		
		return SUCCESS;
	}
	
	// ���(Comment)����
	public String execute2() throws Exception {
		
		member_id = (String)ActionContext.getContext().getSession().get("member_id");
		member = (MemberVO) sqlMapper.queryForObject("checkid",member_id);
		
		// �Ķ���͸� ������ ��ü�� ���� ������� ������ ��ü ����
		cparamClass = new Used_CommentVO();
		cresultClass = new Used_CommentVO();
		
		// ���� �� �׸� ����
		cparamClass.setUcomment_no(getUcomment_no());
					
		// ��� ���� ���� ����
		sqlMapper.update("deleteUcomment", cparamClass);
		
		return SUCCESS;
	}

	public UsedVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(UsedVO paramClass) {
		this.paramClass = paramClass;
	}

	public UsedVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(UsedVO resultClass) {
		this.resultClass = resultClass;
	}

	public Used_CommentVO getCparamClass() {
		return cparamClass;
	}

	public void setCparamClass(Used_CommentVO cparamClass) {
		this.cparamClass = cparamClass;
	}

	public Used_CommentVO getCresultClass() {
		return cresultClass;
	}

	public void setCresultClass(Used_CommentVO cresultClass) {
		this.cresultClass = cresultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public int getUboard_no() {
		return uboard_no;
	}

	public void setUboard_no(int uboard_no) {
		this.uboard_no = uboard_no;
	}

	public int getUcomment_no() {
		return ucomment_no;
	}

	public void setUcomment_no(int ucomment_no) {
		this.ucomment_no = ucomment_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
	
}