package egovframework.let.test.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.test.service.TestService;
import egovframework.let.test.service.TestVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class TestController {
	
	@Resource(name = "testService")
	private TestService testService;
	
	//TEST 목록 가져오기
	@RequestMapping(value = "/test/selectList.do")
	public String selectList(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(testVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(testVO.getPageUnit());
		paginationInfo.setPageSize(testVO.getPageSize());
		
		testVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		testVO.setLastIndex(paginationInfo.getLastRecordIndex());
		testVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
		List<EgovMap> resultList = testService.selectTestList(testVO);
		model.addAttribute("resultList", resultList);
			
		int totCnt = testService.selectTestListCnt(testVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "test/TestSelectList";
	}
	
	 
	//TEST 가져오기
	@RequestMapping(value = "/test/select.do")
	public String select (TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception {
		TestVO result = testService.selectTest(testVO);
		model.addAttribute("result", result);
		return "test/TestSelect";
	}
	
	 
	//TEST 등록/수정
	@RequestMapping(value = "/test/testRegist.do")
	public String TestRdgist(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception {
		TestVO result = new TestVO();
		if(!EgovStringUtil.isEmpty(testVO.getTestId())) {
			result = testService.selectTest(testVO);
		}
		model.addAttribute("result", result);
		
		return "test/TestRegist";
	}
	
	
	//TEST 등록하기
	@RequestMapping(value = "/test/insert.do")
	public String insert(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId()== null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/test/selectList.do";
		}
		
		testVO.setUserId(user.getId());
		
		testService.insertTest(testVO);
		return "forward:/test/selectList.do";
	}
	
	//TEST 수정하기
	@RequestMapping(value = "/test/update.do")
	public String update(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId()== null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/test/selectList.do";
		}
		
		testVO.setUserId(user.getId());
		
		testService.updateTest(testVO);
		return "forward:/test/selectList.do";
	}
	
	//TEST 삭제하기
	@RequestMapping(value = "/test/delete.do")
	public String delete(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId()== null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/test/selectList.do";
		}
		
		testVO.setUserId(user.getId());
		
		testService.deleteTest(testVO);
		return "forward:/test/selectList.do";
	}
	

	
}
