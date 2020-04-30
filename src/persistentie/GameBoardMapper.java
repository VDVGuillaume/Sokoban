package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Game;
import domein.GameBoard;
import domein.User;

public class GameBoardMapper extends BaseMapper {

	private TileMapper tileMapper;

	/** constructor GameBoardMapper */
	public GameBoardMapper() {
		tileMapper = new TileMapper();
	}

	/**
	 * method getGameBoards(String gameName) return list of Gameboards related to a
	 * given gamename
	 */
	public List<GameBoard> getGameBoards(String gameName) {
		List<GameBoard> gameBoards = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select id from GAMEBOARD where GameName = ?";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, gameName);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var gameBoardId = rs.getInt(1);
				var tiles = tileMapper.getTiles(gameBoardId);
				gameBoards.add(new GameBoard(gameBoardId, tiles));
			}
		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
		}

		return gameBoards;
	}

	/** method saveGameBoard(Game game) to save GameObject in DB */
	public int saveGameBoard(GameBoard gameBoard, String gameName) {
		int gameboardID = 0;
		
		

		int generatedKey = -1;
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO GAMEBOARD (GameName) VALUES (?)";
		final String sqlQuery = "SELECT max(id) FROM GAMEBOARD";
		PreparedStatement queryStmt = null;
		Connection conn2 = null;
		

		try {
			conn = createConnection();
			conn2 = createConnection();
			stmt = conn.prepareStatement(sql);
			queryStmt = conn2.prepareStatement(sqlQuery);
			stmt.setString(1, game.getName());

			stmt.executeUpdate();
			
	
			

			ResultSet rs = queryStmt.executeQuery();
			while (rs.next()) {
				gameboardID = rs.getInt(1);
				
			}
			
			tileMapper.insertTiles(game.getSelectedGameBoard().getTiles(), gameboardID);
			
		} catch (SQLException e) 
		{
			// java...
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
			try {
				if (conn != null)
					
					conn.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
			try {
				if (queryStmt != null)
					queryStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn2 != null)
					conn2.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
		return gameboardID;

	}

	
	public void insertGameBoard(GameBoard gameBoard) {
		//TODO this should also call TileMapper
		// Same functionality as saveGameBoard but renamed to reflect action that happens
	}



	
	public void updateGameBoard(GameBoard gameBoard) {
		tileMapper.updateTiles(gameBoard.getId(), gameBoard.getTiles());
	}

}
