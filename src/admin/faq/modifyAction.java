package admin.faq;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import user.member.MemberVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.io.IOException;

public class modifyAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;

	private FAQVO paramClass; //�Ķ���͸� ������ ��ü
	private FAQVO resultClass; //���� ��� ���� ������ ��ü
	private MemberVO member;
	
	private String member_id;

	private int currentPage;	//���� ������
	
	private int faq_no;
	private String faq_sub;
	private String faq_content;
	private String faq_type;

	// ������
	public modifyAction() throws IOException {
		
		reader = Resources.getResourceAsReader("sqlMapConfig.xml"); // sqlMapConfig.xml ������ ���������� �����´�.
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader); // sqlMapConfig.xml�� ������ ������ sqlMapper ��ü ����.
		reader.close();
	}

	// �Խñ� ����//
	public String execute() throws Exception {
		member_id = (String)ActionContext.getContext().getSession().get("member_id");
		member = (MemberVO) sqlMapper.queryForObject("checkid",member_id);
		//�Ķ���Ϳ� ����Ʈ ��ü ����.
		paramClass = new FAQVO();
		resultClass = new FAQVO();

		// ������ �׸� ����.
		paramClass.setFaq_no(getFaq_no());
		paramClass.setFaq_sub(faq_sub);
		paramClass.setFaq_content(faq_content);
		paramClass.setFaq_type(faq_type);

		// �ϴ� �׸� �����Ѵ�.
		sqlMapper.update("updateFaq", paramClass);

		// ������ ������ view �������� �̵�.
		resultClass = (FAQVO) sqlMapper.queryForObject("selectOneFaq", faq_no);

		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		modifyAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		modifyAction.sqlMapper = sqlMapper;
	}

	public FAQVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(FAQVO paramClass) {
		this.paramClass = paramClass;
	}

	public FAQVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(FAQVO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}

	public String getFaq_sub() {
		return faq_sub;
	}

	public void setFaq_sub(String faq_sub) {
		this.faq_sub = faq_sub;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

	public String getFaq_type() {
		return faq_type;
	}

	public void setFaq_type(String faq_type) {
		this.faq_type = faq_type;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	
}
