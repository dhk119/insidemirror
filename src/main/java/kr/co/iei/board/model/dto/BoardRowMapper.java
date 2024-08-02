package kr.co.iei.board.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardRowMapper implements RowMapper<Board>{

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board b = new Board();
		b.setBoardNo(rs.getInt("board_no"));
		b.setBoardTitle(rs.getString("board_title"));
		b.setMemberNo(rs.getInt("member_no"));
		b.setReadCount(rs.getInt("read_count"));
		b.setBoardDate(rs.getString("board_date"));
		b.setBoardContent(rs.getString("board_content"));
		return b;
	}

}
