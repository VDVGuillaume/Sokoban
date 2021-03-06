package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domein.GameBoard;
import domein.Tile;
import domein.TileTypes;

public class TileMapper extends BaseMapper {
	public Tile[][] getTiles(int gameBoardId) {
		Tile[][] tiles = new Tile[10][10];

		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select row_index, column_index, type, contains_player from TILE where gameboard_id = ? order by row_index, column_index";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, gameBoardId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var rowIndex = rs.getInt(1);
				var columnIndex = rs.getInt(2);
				TileTypes type = TileTypes.valueOf(rs.getString(3));
				var containsPlayer = rs.getBoolean(4);

				tiles[rowIndex][columnIndex] = new Tile(type, containsPlayer, columnIndex, rowIndex);
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

		return tiles;
	}

	public void insertTiles(Tile[][] tiles, int gameBoardId) {
		Connection conn = null;
		String sql = "INSERT INTO TILE(gameboard_id, row_index, column_index, type, contains_player) VALUES (?, ?, ?, ?, ?)";

		try {
			conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int rowIndex = 0;
			for (Tile[] tileRow : tiles) {
				int columnIndex = 0;

				for (Tile tile : tileRow) {
					stmt.setInt(1, gameBoardId);
					stmt.setInt(2, rowIndex);
					stmt.setInt(3, columnIndex);
					stmt.setString(4, tile.getTileType().toString());
					stmt.setBoolean(5, tile.getContainsPlayer());
					stmt.addBatch();

					columnIndex++;
				}
				rowIndex++;
			}

			stmt.executeBatch();
		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		}
	}

	public void updateTiles(int gameBoardId, Tile[][] tiles) {
		Connection conn = null;
		String sql = "UPDATE TILE SET type = ?, contains_player = ? WHERE  gameboard_id = ? and row_index = ? and column_index = ?";

		try {
			conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int rowIndex = 0;
			for (Tile[] tileRow : tiles) {
				int columnIndex = 0;

				for (Tile tile : tileRow) {
					stmt.setString(1, tile.getTileType().toString());
					stmt.setBoolean(2, tile.getContainsPlayer());
					stmt.setInt(3, gameBoardId);
					stmt.setInt(4, rowIndex);
					stmt.setInt(5, columnIndex);
					stmt.addBatch();

					columnIndex++;
				}
				rowIndex++;
			}

			stmt.executeBatch();
		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		}
	}
}
