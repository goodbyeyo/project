package user.used;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import user.member.MemberVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class modifyAction extends ActionSupport {
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private UsedVO paramClass; // �Ķ���͸� ������ ��ü
	private UsedVO resultClass; // ���� ��� ���� ���� �� ��ü
	private MemberVO member;
	
	private int currentPage;
	
	private int uboard_no;
	private String uboard_sub;
	private String uboard_member_name;
	private String uboard_passwd;
	private String uboard_content;
	private String old_file;
	private String member_id;
	
	
	private File upload; // ���� ��ü
	private String uploadContentType; // ������Ÿ��
	private String uploadFileName; // ���� �̸�
	private String fileUploadPath = "C:\\Users\\hsp\\eclipse-workspace\\Eoullim\\WebContent\\upload\\"; // ���ε���
	
	// ������
	public modifyAction() throws IOException {
		
		reader = Resources.getResourceAsReader("sqlMapConfig.xml"); // sqlMapConfig.xml ������ ���������� �����´�.
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader); // sqlMapConfig.xml�� ������ ������ sqlMapper ��ü ����.
		reader.close();
	}
	
	// �Խñ� ����
	public String execute() throws Exception {
		member_id = (String)ActionContext.getContext().getSession().get("member_id");
		member = (MemberVO) sqlMapper.queryForObject("checkid",member_id);
		// �Ķ���͸� ������ ��ü�� ����������� ������ ����Ʈ ��ü ����
		paramClass = new UsedVO();
		resultClass = new UsedVO();
		
		// ������ �׸� ����
		paramClass.setUboard_no(getUboard_no());
		paramClass.setUboard_sub(getUboard_sub());
		paramClass.setUboard_member_name(member.getMember_name());
		paramClass.setUboard_passwd(member.getMember_passwd1());
		paramClass.setUboard_content(getUboard_content());
		
		// �ϴ� ���� 5���� �׸���� �켱 �����Ѵ�
		sqlMapper.update("updateUboard", paramClass);
		
		// ������ ������ ���ε� �Ǿ��ٸ� ������ ���ε��ϰ� DB�� file�׸��� ����
		if(getUpload() != null) {
			
			// ���� ������ ����� ���� �̸��� Ȯ���� ����
			String file_name = "file_" + getUboard_no();
			String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.') + 1,
					getUploadFileName().length());
			
			// ���� ���� ����
			File deleteFile = new File(fileUploadPath + getOld_file());
			deleteFile.delete();
			
			// �� ���� ���ε�
			File destFile = new File(fileUploadPath + file_name + "." + file_ext);
			FileUtils.copyFile(getUpload(), destFile);
			
			// ���� ���� �Ķ���� ����.
			paramClass.setUboard_file_orgname(getUploadFileName());
			paramClass.setUboard_file_savname(file_name + "." + file_ext);
			
			// ���� ���� ������Ʈ 
			sqlMapper.update("updateFileUboard", paramClass);
		}
		
		// ������ ������ view �������� �̵�(3�� �ּ�)
		// �� �̷��� �ϴ°��� Ȯ���غ���??
		// �� ��ȣ�� �ش��ϴ� 1���� �����͸� �����ͼ� resultClass �� ����
		resultClass = (UsedVO) sqlMapper.queryForObject("selectOneUboard", getUboard_no());
		
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getUboard_no() {
		return uboard_no;
	}

	public void setUboard_no(int uboard_no) {
		this.uboard_no = uboard_no;
	}

	public String getUboard_sub() {
		return uboard_sub;
	}

	public void setUboard_sub(String uboard_sub) {
		this.uboard_sub = uboard_sub;
	}

	public String getUboard_member_name() {
		return uboard_member_name;
	}

	public void setUboard_member_name(String uboard_member_name) {
		this.uboard_member_name = uboard_member_name;
	}

	public String getUboard_passwd() {
		return uboard_passwd;
	}

	public void setUboard_passwd(String uboard_passwd) {
		this.uboard_passwd = uboard_passwd;
	}

	public String getUboard_content() {
		return uboard_content;
	}

	public void setUboard_content(String uboard_content) {
		this.uboard_content = uboard_content;
	}

	public String getOld_file() {
		return old_file;
	}

	public void setOld_file(String old_file) {
		this.old_file = old_file;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
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