package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Game;
import domein.GameBoard;
import domein.User;

public class GameMapper extends BaseMapper {
	private GameBoardMapper gameBoardMapper;
	private UserMapper userMapper;

	public GameMapper() {
		gameBoardMapper = new GameBoardMapper();
	}

	public List<String> getGames() {
		List<String> games = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select name from GAME order by name";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var gameName = rs.getString(1);
				games.add(gameName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return games;
	}

	public List<String> getGames(String username) {
		List<String> games = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select name from GAME WHERE createdByUser=? order by name";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var gameName = rs.getString(1);
				games.add(gameName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return games;
	}

	public Game getGame(String gameName) {
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select name from GAME where name = ?";
		Game game = null;

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, gameName);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				var gameBoards = gameBoardMapper.getGameBoards(gameName);
				game = new Game(gameName, gameBoards);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return game;
	}

	public void insertGame(Game game, String username) {

		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "INSERT INTO GAME(name,createdByUser) VALUES (?,?)";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, game.getName());
			stmt.setString(2, username);

			stmt.executeUpdate();

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
	}

	public void deleteSelectedGameBoard(String gamename, int gameboardId) {
		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "DELETE FROM GAMEBOARD WHERE GameName=gamename AND id=gameboardId";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.executeUpdate();

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
	}
	
	public void updateGame(Game game) {
		// TODO UC7 & UC8
		
		for(GameBoard gameBoard : game.getGameBoards()) {
			gameBoardMapper.updateGameBoard(gameBoard);
		}
	}
}
