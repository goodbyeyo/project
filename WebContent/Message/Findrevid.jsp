<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<link href="/Eoullim/user/member/style.css" rel="stylesheet" type="text/css">
<head>
<script type="text/javascript">
	function windowclose(id){
		opener.document.myform.msg_rev_id.value= id;
		self.close();
	}
</script>
<link rel="stylesheet" href="Resource/css/pages/import.css"/> 
    
	<script src="Resource/js/common/libs/modernizr.custom.js"></script>
	<script src="Resource/js/common/libs/greensock/TweenMax.min.js"></script>
	<script src="Resource/js/common/libs/greensock/easing/EasePack.min.js"></script>
	<script src="Resource/js/common/libs/jquery-1.9.1.min.js"></script>

	<script src="Resource/js/common/libs/slick.min.js"></script>
    <script src="Resource/js/common/libs/jquery.event.move.js"></script>
    <script src="Resource/js/common/libs/jquery.rwdImageMaps.min.js"></script>

	<script src="Resource/js/common/styleship/mighty.base.1.5.5.min.js"></script>
	<script src="Resource/js/common/styleship/mighty.slide.4.2.2.min.js"></script>
	<script src="Resource/js/common/styleship/matizResizeMap.1.0.0.min.js"></script>
	<script src="Resource/js/common/styleship/matizCoverLayer.3.1.0.min.js"></script>
	<script src="Resource/js/common/styleship/mighty.sort.1.2.1.min.js"></script>
	<script src="Resource/js/common/libs/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="Resource/js/common/styleship/matizResizeMap.1.0.0.min.js"></script>
	<script src="Resource/js/pages/jquery.youtubebackground.js"></script>

	<script src="Resource/js/common/default.js"></script>
	<script src="Resource/js/common/cookie.js"></script>

	<script type="text/javascript" src="//wcs.naver.net/wcslog.js"> </script>
	<link rel="stylesheet" href="Resource/css/pages/member.css">
	<script src="Resource/js/pages/login.js"></script>
	<script src="Resource/js/pages/member.js"></script>
	<link rel="stylesheet" href="Resource/css/common/jquery-ui.css">	
	<link rel="stylesheet" href="Resource/css/pages/table.css">
	<link rel="stylesheet" href="Resource/css/pages/mypage.css">
	<link rel="stylesheet" href="Resource/css/pages/customer.css">
	<script src="Resource/js/pages/mypage.js"></script>
	<link rel="stylesheet" href="Resource/css/pages/list.css">
	<script src="Resource/js/pages/list.js"></script>
	<script src="Resource/js/pages/faq.js"></script>
</head>
<body>
<center>
	<!-- start of :: contents -->
		<div id="contents">
			<div class="customer_wrap">
				<!-- lnb_wrap -->
				<div class="con_wrap">			
					</div>
					<h2>회원선택 </h2><br><br>
					<div class="faq_table customer_faq_table">
						<table>
							<colgroup>
								<col style="width:12%"/>
								<col/>
								<col style="width:18%"/>
							</colgroup>
							<thead>
								<tr>
									<th width = "20%">회원번호</th>
									<th width = "20%">회원아이디</th>
									<th width = "20%">회원 이름</th>
									<th width = "20%">가입날짜</th>
									<th width = "20%">선택</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="list" status="stat">
								<tr>
									<td><s:property value="member_no" /></td>
									<td><s:property id = "member_id" value="member_id" /></td>
									<td><s:property value = "member_name"/></td>
									<td><s:property value="member_regdate" /></td>
									<s:set name = "id" value = "member_id"/>
									<td><input type="button" class="hreflink" value="선택" onclick="windowclose('<s:property value = "member_id"/>')"/></td>
								</tr>
							</s:iterator>
							<s:if test="list.size() <= 0">
								<tr>
									<td colspan="5" align="center">회원이 없습니다.</td>
								</tr>
							</s:if>
							<tr align="center">
								<td colspan="5"><s:property value="pagingHtml" escape="false" /></td>
							</tr>
							</tbody>
						</table>

					</div>
				</div>
				<!-- //con_wrap -->
			</div>
			<!--//customer_wrap-->
		</div>
		<!-- end of :: contents -->
</center>
</body>
</html>