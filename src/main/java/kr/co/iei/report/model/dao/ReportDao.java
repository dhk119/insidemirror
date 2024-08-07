package kr.co.iei.report.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.customer.dto.CustomerRowMapper;
import kr.co.iei.report.model.dto.ReportRowMapper;

@Repository
public class ReportDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private ReportRowMapper reportRowMapper;
	
	@Autowired
	private CustomerRowMapper customerRowMapper;

	public List selectReportList(int start, int end) {
		String query = "select * from (select rownum as rnum, r.* from (select report.report_no, customer.inq_title, report.report_date, customer.customer_list, customer.status from report left join customer on report.member_no = customer.member_no order by 1 desc)r) where rnum between ? and ?";
		Object[] params = {start,end};
		List reportList = jdbc.query(query, reportRowMapper, params);
		return reportList;
	}//selectReportList

	public int selectReportTotalCount() {
		String query = "select count(*) from customer";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectReportTotalCount

	
	public List selectFiveReport() {
		String query = "select * from (select rownum as rnum, n.* from (select * from customer order by 1 desc)n) where rnum between 1 and 5";
		List fiveReportList = jdbc.query(query, customerRowMapper);
		return fiveReportList;
	}//selectFiveReport
}
